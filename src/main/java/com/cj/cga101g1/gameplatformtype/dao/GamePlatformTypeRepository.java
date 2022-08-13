package com.cj.cga101g1.gameplatformtype.dao;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GamePlatformTypeRepository extends CrudRepository<GamePlatformTypeVO,Integer> {

    @Query(value = "SELECT gamePlatformNo,gamePlatformName from gameplatformtype where gamePlatformName = ?1"
            ,nativeQuery = true)
    GamePlatformTypeVO FindByGamePlatformName(String gamePlatformName);

    @Query(value = "SELECT gamePlatformNo,gamePlatformName from gameplatformtype where gamePlatformName like %?1%"
            ,nativeQuery = true)
    List<GamePlatformTypeVO> FindByKeywordName(String KeywordName);

}
