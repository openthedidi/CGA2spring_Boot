package com.cj.cga101g1.gametype.service;

import com.cj.cga101g1.gametype.util.GameTypeVO;

import java.util.List;

public interface GameTypeService {
    /**** 新增GameType ****/
    GameTypeVO newGameType(String gameTypeName);

    /**** 新增批次的GameTypes ****/
    List<GameTypeVO> newOneGameTypes(List<GameTypeVO> list);

    /**** 查詢批次的GameTypes ****/
    List<GameTypeVO> getAllGameTypes();

    /**** 查詢批次的GameTypesByResultSetExtractor ****/
    List<GameTypeVO> getAllGameTypesByRs();

    /**** 查詢一種的getGameType ****/
    GameTypeVO getOneGameType(Integer gameTypeNo);
}
