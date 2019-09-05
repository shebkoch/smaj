package com.ogont.smaj.controller;

import com.ogont.smaj.model.FullMatch;
import com.ogont.smaj.model.MatchEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.service.IFullMatchService;
import com.ogont.smaj.service.IMatchService;
import com.ogont.smaj.service.IPlayerResultService;
import com.ogont.smaj.service.IPlayerService;
import com.ogont.smaj.service.impl.PlayerResultService;
import com.ogont.smaj.util.Pair;
import com.ogont.smaj.util.PlacedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MatchController {
    @Resource
    IFullMatchService fullMatchService;
    @Resource
    PlayerResultService playerResultService;

    @PostMapping("/match/{isEndString}")
    @ResponseStatus(HttpStatus.OK)
    public void matchSave(@RequestBody FullMatch match, @PathVariable String isEndString) {
        fullMatchService.save(match, isEndString.equals("true"));
    }
}
