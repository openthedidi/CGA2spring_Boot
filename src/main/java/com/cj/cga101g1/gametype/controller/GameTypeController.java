package com.cj.cga101g1.gametype.controller;


import com.cj.cga101g1.gametype.util.GameTypeVO;
import com.cj.cga101g1.gametype.service.GameTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("CGA101G1/gametype")
@RestController
public class GameTypeController {

    @Autowired
    private GameTypeVO gameTypeVO;
    @Autowired
    private GameTypeService gameTypeService;


    @PostMapping("/newGameType")
    public GameTypeVO newGameType(@RequestBody GameTypeVO gameTypeVO1){
        gameTypeVO= gameTypeService.newGameType(gameTypeVO1.getGameTypeName());
        return gameTypeVO;
    }

    @Transactional
    @PostMapping("/newGameTypes")
    public List<GameTypeVO> newGameTypes(@RequestBody List<GameTypeVO> list){
        List<GameTypeVO> listR = gameTypeService.newOneGameTypes(list);
        return listR;
    }

    @GetMapping("/getAllGameType")
    public List<GameTypeVO> getAllGameType(){
        List<GameTypeVO> list = gameTypeService.getAllGameTypes();
        return  list;
    }

    @GetMapping("/getGametypesByRs")
    public List<GameTypeVO> getGameTypesByRs(){
        List<GameTypeVO> list = gameTypeService.getAllGameTypesByRs();
        return  list;
    }


    @GetMapping("/getGametype/{gameTypeNo}")
    public GameTypeVO getGameType(@PathVariable Integer gameTypeNo){
        gameTypeVO = gameTypeService.getOneGameType(gameTypeNo);
        return gameTypeVO;
    }


}
