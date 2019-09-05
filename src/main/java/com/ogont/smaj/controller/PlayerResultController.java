package com.ogont.smaj.controller;

import com.ogont.smaj.model.MatchEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.service.impl.MatchService;
import com.ogont.smaj.service.impl.PlayerResultService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class PlayerResultController {

    @Resource
    PlayerResultService playerResultService;
    @Resource
    MatchService matchService;

    @GetMapping("/last_info/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PlayerResultEntity lastInfo(@PathVariable Integer id){
        return playerResultService.getLast(id);
    }
}
