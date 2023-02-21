package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.List;

@Component("GamePlatformTypeServiceImpRedisVer")
@CacheConfig(cacheNames = "gamePlatformTypeService")
public class GamePlatformTypeServiceImpRedisVer implements GamePlatformTypeService{
    @Autowired
    private GamePlatformTypeDAO gamePlatformTypeDAO;

    @Override
    @CachePut(key = "#p0")
    public GamePlatformTypeVO newPlatformType(GamePlatformTypeVO gamePlatformTypeVO) throws RuntimeException, SQLException {
        gamePlatformTypeDAO.newType(gamePlatformTypeVO);
        return gamePlatformTypeVO;
    }

    @Override
    @Cacheable(key = "#p0")
    public GamePlatformTypeVO getOneType(Integer gamePlatformTypeNo) {
        return  gamePlatformTypeDAO.getType(gamePlatformTypeNo);
    }

    @Override
    @Cacheable(key = "#p0")
    public GamePlatformTypeVO getOneTypeByName(String gamePlatformTypeName) {
        return gamePlatformTypeDAO.getType(gamePlatformTypeName);
    }

    @Override
    @Cacheable(key = "#p0")
    public List<GamePlatformTypeVO> getOneTypeByKeyName(String keyName) {
        return gamePlatformTypeDAO.getTypeByKeyword(keyName);
    }

    @Override
    @CachePut(key = "#p0.gamePlatformNo")
    public GamePlatformTypeVO updateOneType(GamePlatformTypeVO gamePlatformTypeVO2) {
        GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeDAO.updateOneType(gamePlatformTypeVO2);
        return gamePlatformTypeDAO.updateOneType(gamePlatformTypeVO);
    }

    @Override
    @CacheEvict(key = "#p0.gamePlatformNo",allEntries = true)
    public String deleteOneType(GamePlatformTypeVO gamePlatformTypeVO) {
        return gamePlatformTypeDAO.deleteOneType(gamePlatformTypeVO);
    }

    /**
    *    key生成是呼叫 RedisConfig.wiselyKeyGenerator()方法生成，value = "getAllTypes"
    **/
    @Override
    @Cacheable(value = "getAllTypes", keyGenerator = "wiselyKeyGenerator")
    public List<GamePlatformTypeVO> getAllType() {
        return gamePlatformTypeDAO.getAllType();
    }

}
