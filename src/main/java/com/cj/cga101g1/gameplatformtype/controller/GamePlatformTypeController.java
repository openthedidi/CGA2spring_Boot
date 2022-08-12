package com.cj.cga101g1.gameplatformtype.controller;


import com.cj.cga101g1.gameplatformtype.service.GamePlatformTypeServiceImp;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gamePlatformType")
public class GamePlatformTypeController {
    @Autowired
    private GamePlatformTypeServiceImp gamePlatformTypeServiceImp;


    @PostMapping("/newType")
    public GamePlatformTypeVO newType(@RequestBody GamePlatformTypeVO gamePlatformTypeVO){
        System.out.println(gamePlatformTypeVO.getGamePlatformName());
        gamePlatformTypeServiceImp.newType(gamePlatformTypeVO);
        return gamePlatformTypeVO;
    }

}
