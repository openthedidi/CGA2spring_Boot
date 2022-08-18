package com.cj.cga101g1.gameplatformtype.dao;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;

import java.util.List;


public interface GamePlatformTypeDAO {

    /******  新增一個PlatformType  ********/
    public GamePlatformTypeVO newType(GamePlatformTypeVO gamePlatformTypeVO);

    /******  查詢一個PlatformType由PK  ********/
    public GamePlatformTypeVO getType(Integer gamePlatformTypeNo);

    /******  查詢一個PlatformType由完整名稱  ********/
    public GamePlatformTypeVO getType(String gamePlatformTypeName);

    /******  查詢一個PlatformType由關鍵字名稱  ********/
    public List<GamePlatformTypeVO> getTypeByKeyword(String keyword);

    /******  查詢全部的PlatformType  ********/
    public List<GamePlatformTypeVO> getAllType();


    /******  修改一個PlatformType由PK  ********/
    public GamePlatformTypeVO updateOneType(GamePlatformTypeVO gamePlatformTypeVO);

    /******  刪除一個PlatformType由PK  ********/
    public String deleteOneType(GamePlatformTypeVO gamePlatformTypeVO);

}
