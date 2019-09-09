package com.ogont.smaj.controller;

import com.ogont.smaj.model.*;
import com.ogont.smaj.service.*;
import com.ogont.smaj.service.impl.PlayerResultService;
import com.ogont.smaj.util.Pair;
import com.ogont.smaj.util.PlacedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class MatchController {
    @Resource
    IFullMatchService fullMatchService;
    @Resource
    PlayerResultService playerResultService;
    @Resource
    IMatchService matchService;
    @Resource
    IFactionDistributorService factionDistributorService;

    @PostMapping("/match/{isEndString}")
    @ResponseStatus(HttpStatus.OK)
    public void matchSave(@RequestBody FullMatch match, @PathVariable String isEndString) {
        fullMatchService.save(match, isEndString.equals("true"));
    }
    @RequestMapping(value = "/distribute", method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Pair<PlayerEntity, FactionComboEntity>> distribute(@RequestBody DistributeData data) {
        return distribute2(data);
    }
    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Pair<PlayerEntity, FactionComboEntity>> distribute2(@RequestBody DistributeData data) {
        Map<PlayerEntity, FactionComboEntity> map = factionDistributorService.distribute(data);
        List<Pair<PlayerEntity, FactionComboEntity>> list = new ArrayList<>();
        for(Map.Entry<PlayerEntity, FactionComboEntity> entry : map.entrySet()){
            list.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    @GetMapping("/match/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody FullMatch getMatch(@PathVariable Integer id) {
        return fullMatchService.getByMatchId(id);
    }

    @GetMapping("/matches")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<MatchEntity> getMatches() {
        List<MatchEntity> list = matchService.getAll();
        list.sort(Comparator.comparing(MatchEntity::getEtime).reversed());
        return list;
    }
}
