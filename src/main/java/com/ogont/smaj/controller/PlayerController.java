package com.ogont.smaj.controller;

import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.service.impl.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@Controller
public class PlayerController {
    @Resource
    PlayerService playerService;

    @GetMapping("/players")
    public @ResponseBody List<PlayerEntity> players(){
        List<PlayerEntity> playerEntities = playerService.getAll();

        playerEntities.sort(Comparator.comparingInt(PlayerEntity::getMmr).reversed());
        return playerEntities;
    }
    @GetMapping("/player/{id}")
    public @ResponseBody PlayerEntity player(@PathVariable Integer id){
        return playerService.findById(id).get();
    }
}
