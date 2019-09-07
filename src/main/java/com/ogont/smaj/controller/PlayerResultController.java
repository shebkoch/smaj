package com.ogont.smaj.controller;

import com.ogont.smaj.model.MatchEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.service.impl.MatchService;
import com.ogont.smaj.service.impl.PlayerResultService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PlayerResultController {

    @Resource
    PlayerResultService playerResultService;
    @Resource
    MatchService matchService;

    @GetMapping("/last_info/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PlayerResultEntity lastInfo(@PathVariable Integer id){
        return playerResultService.getLast(id);
    }

    @GetMapping("/results/{id}")
    @ResponseStatus(HttpStatus.OK)

    public @ResponseBody List<PlayerResultEntity> getAll(@PathVariable Integer id){
        return playerResultService.findAllByPlayerId(id);
    }
}
