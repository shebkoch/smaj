package com.ogont.smaj.service;

import com.ogont.smaj.model.IGenericEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface IService<T extends IGenericEntity<ID>, ID > {
    CrudRepository<T, ID> getRepository();

    default List<T> getAll(){
        List<T> list = new ArrayList<>();
        Iterable<T> all = findAll();
        for(T entity : all){
            list.add(entity);
        }
        return list;
    }

    default <S extends T> S save(S var1){
        return getRepository().save(var1);
    }

    default <S extends T> Iterable<S> saveAll(Iterable<S> var1){
        return getRepository().saveAll(var1);
    }

    default Optional<T> findById(ID var1){
        return getRepository().findById(var1);
    }

    default boolean existsById(ID var1){
        return getRepository().existsById(var1);
    }

    default Iterable<T> findAll(){
        return getRepository().findAll();
    }

    default Iterable<T> findAllById(Iterable<ID> var1){
        return getRepository().findAllById(var1);
    }

    default long count(){
        return getRepository().count();
    }

    default void deleteById(ID var1){
        getRepository().deleteById(var1);
    }

    default void delete(T var1){
        getRepository().delete(var1);
    }

    default void deleteAll(Iterable<? extends T> var1){
        getRepository().deleteAll(var1);
    }

    default void deleteAll(){
        getRepository().deleteAll();
    }

}
