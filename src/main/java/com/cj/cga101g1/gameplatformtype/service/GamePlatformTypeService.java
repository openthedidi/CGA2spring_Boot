package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;

import java.util.List;


public interface GamePlatformTypeService {

    /******  新增一個PlatformType  ********/
    public GamePlatformTypeVO newPlatformType(GamePlatformTypeVO gamePlatformTypeVO);

    /******  查詢一個PlatformType由PK  ********/
    public GamePlatformTypeVO getOneType(Integer gamePlatformTypeNo);

    /******  查詢一個PlatformType由完整名稱  ********/
    public GamePlatformTypeVO getOneTypeByName(String gamePlatformTypeName);

    /******  查詢一個PlatformType由完整名稱  ********/
    public List<GamePlatformTypeVO> getOneTypeByKeyName(String keyName);

    /******  修改一個PlatformType由PK  ********/
    public GamePlatformTypeVO updateOneType(GamePlatformTypeVO gamePlatformTypeVO);

    /******  刪除一個PlatformType由PK  ********/
    public String deleteOneType(GamePlatformTypeVO gamePlatformTypeVO);

}
