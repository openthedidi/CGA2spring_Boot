package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GamePlatformTypeServiceImp implements GamePlatformTypeService{

    @Autowired
    private GamePlatformTypeDAO gamePlatformTypeDAO;

    @Override
    public GamePlatformTypeVO newPlatformType(GamePlatformTypeVO gamePlatformTypeVO) {
        gamePlatformTypeDAO.newType(gamePlatformTypeVO);
        return gamePlatformTypeVO;
    }

    @Override
    public GamePlatformTypeVO getOneType(Integer gamePlatformTypeNo) {
        return  gamePlatformTypeDAO.getType(gamePlatformTypeNo);
    }

    @Override
    public GamePlatformTypeVO getOneTypeByName(String gamePlatformTypeName) {
        return gamePlatformTypeDAO.getType(gamePlatformTypeName);
    }

    @Override
    public List<GamePlatformTypeVO> getOneTypeByKeyName(String keyName) {
        return gamePlatformTypeDAO.getTypeByKeyword(keyName);
    }

    @Override
    public GamePlatformTypeVO updateOneType(GamePlatformTypeVO gamePlatformTypeVO2) {
        GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeDAO.updateOneType(gamePlatformTypeVO2);
        return gamePlatformTypeDAO.updateOneType(gamePlatformTypeVO);
    }

    @Override
    public String deleteOneType(GamePlatformTypeVO gamePlatformTypeVO) {
        return gamePlatformTypeDAO.deleteOneType(gamePlatformTypeVO);
    }
}
