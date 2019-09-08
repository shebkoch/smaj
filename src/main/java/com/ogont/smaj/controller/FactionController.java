package com.ogont.smaj.controller;

import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.service.impl.FactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@Controller
public class FactionController {

    @Resource
    FactionService factionService;

    @GetMapping("/factions")
    public @ResponseBody
    List<FactionEntity> factions(){
        List<FactionEntity> list = factionService.getAll();
        list.sort(Comparator.comparingInt(FactionEntity::getMatchCount).reversed());
        return list;
    }
}
