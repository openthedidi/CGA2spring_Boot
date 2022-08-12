package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeRepository;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
@Component
public class GamePlatformTypeServiceImp {


    @Autowired
    private GamePlatformTypeRepository gamePlatformTypeRepository;

    public GamePlatformTypeVO newType(GamePlatformTypeVO gamePlatformTypeVO){
        System.out.println(gamePlatformTypeVO.getGamePlatformName());
        gamePlatformTypeRepository.save(gamePlatformTypeVO);
        return gamePlatformTypeVO;
    }

}
