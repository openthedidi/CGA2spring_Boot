package com.cj.cga101g1.gameNews.controller;


import com.cj.cga101g1.gameNews.model.GameNews;
import com.cj.cga101g1.gameNews.service.GameNewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(list != null){
            return ResponseEntity.ok(list);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping(value = "getPicUrl", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] getPicUrl(@RequestParam Integer gameNewsNo){
        return gameNewsService.getPicUrl(gameNewsNo);
    }

}
