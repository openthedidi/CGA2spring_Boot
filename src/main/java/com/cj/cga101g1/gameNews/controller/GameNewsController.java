package com.cj.cga101g1.gameNews.controller;


import com.cj.cga101g1.gameNews.model.GameNews;
import com.cj.cga101g1.gameNews.service.GameNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CGA101G1/GameNews")
public class GameNewsController {

    @Autowired
    private GameNewsService gameNewsService;


    @GetMapping("/findGameNewsByMultiKey")
    public ResponseEntity<List<GameNews>> findGameNewsByMultiKey(
            @RequestParam(required = false) String gameNewsContent,
            @RequestParam(required = false) String gameNewsTitle) {
        List<GameNews> list = gameNewsService.findGameNewsByMultiKey(gameNewsContent, gameNewsTitle);
        return ResponseEntity.ok(list);
    }

}
