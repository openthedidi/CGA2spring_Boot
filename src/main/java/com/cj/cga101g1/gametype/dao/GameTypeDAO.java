package com.cj.cga101g1.gametype.dao;

import com.cj.cga101g1.gametype.util.GameTypeVO;

import java.util.List;

public interface GameTypeDAO {
    /**** 新增GameType ****/
    GameTypeVO newGameType(String gameTypeName);

    /**** 新增批次的GameTypes ****/
    List<GameTypeVO> newGameTypes(List<GameTypeVO> list);

    /**** 查詢批次的GameTypes ****/
    List<GameTypeVO> getGameTypes();

    /**** 查詢批次的GameTypesByResultSetExtractor ****/
    List<GameTypeVO> getGameTypesByRs();

    /**** 查詢一種的getGameType ****/
    GameTypeVO getGameType(Integer gameTypeNo);

}
