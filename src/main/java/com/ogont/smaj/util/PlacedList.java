package com.ogont.smaj.util;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class PlacedList<T> extends ArrayList<Pair<Integer, ArrayList<T>>> {

    public void add(Integer score, T entity){
        ArrayList<T> list = null;
        for (Iterator<Pair<Integer, ArrayList<T>>> it = super.iterator(); it.hasNext(); ) {
            Pair<Integer, ArrayList<T>> entry = it.next();
            if(entry.getKey().equals(score)) list = entry.getValue();
        }
        if(list == null) {
            list = new ArrayList<>();
            add(new Pair<Integer, ArrayList<T>>(score, list){
            });
        }

        list.add(entity);
    }
    public int placeCount(){
        int place = 0;
        for (Pair<Integer, ArrayList<T>> entity : this){
            place = entity.getKey();
        }
        return place;
    }
    public int maxScore(){
        ArrayList<Pair<Integer, ArrayList<T>>> list = new ArrayList<>(PlacedList.this);
        list.sort(comparingInt(Pair::getKey));
        return list.get(list.size() -1).getKey();
    }

    @Override
    public Iterator<Pair<Integer, ArrayList<T>>> iterator() {
        return new PlacedItr();
    }

    private class PlacedItr implements Iterator<Pair<Integer, ArrayList<T>>> {

        private ArrayList<Pair<Integer, ArrayList<T>>> list;
        private int current = 0;
        private Integer currentPlace = 0;
        public PlacedItr() {
            list = new ArrayList<>(PlacedList.this);
            list.sort(comparingInt(Pair::getKey));
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public Pair<Integer, ArrayList<T>> next() {
            current++;
            Pair<Integer, ArrayList<T>> pair = list.get(size() - current);

            Pair<Integer, ArrayList<T>> result = new Pair<>(currentPlace, pair.getValue());

            currentPlace += pair.getValue().size();
            return result;
        }
    }
}
