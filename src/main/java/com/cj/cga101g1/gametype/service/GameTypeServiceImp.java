package com.cj.cga101g1.gametype.service;

import com.cj.cga101g1.gametype.dao.GameTypeDAO;
import com.cj.cga101g1.gametype.util.GameTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameTypeServiceImp implements GameTypeService{

    @Autowired
    private GameTypeDAO gameTypeDAO;


    @Override
    public GameTypeVO newGameType(String gameTypeName) {
        GameTypeVO gameTypeVO = gameTypeDAO.newGameType(gameTypeName);
        return gameTypeVO;
    }

    @Override
    public List<GameTypeVO> newOneGameTypes(List<GameTypeVO> list2) {
        List<GameTypeVO> list = gameTypeDAO.newGameTypes(list2);
        return list;
    }

    @Override
    public List<GameTypeVO> getAllGameTypes() {
        List<GameTypeVO> list = gameTypeDAO.getGameTypes();
        return list;
    }

    @Override
    public List<GameTypeVO> getAllGameTypesByRs() {
        List<GameTypeVO> list = gameTypeDAO.getGameTypesByRs();
        return list;
    }

    @Override
    public GameTypeVO getOneGameType(Integer gameTypeNo) {
        GameTypeVO gameTypeVO = gameTypeDAO.getGameType(gameTypeNo);
        return gameTypeVO;
    }
}
