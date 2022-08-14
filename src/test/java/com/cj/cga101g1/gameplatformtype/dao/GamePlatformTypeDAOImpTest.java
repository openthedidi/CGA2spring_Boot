package com.cj.cga101g1.gameplatformtype.dao;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GamePlatformTypeDAOImpTest {

    @Autowired
    private GamePlatformTypeVO gamePlatformTypeVO;
    @Autowired
    private GamePlatformTypeRepository gamePlatformTypeRepository;

    @Transactional
    @Test
    public void newType() {
        gamePlatformTypeVO.setGamePlatformName("Xbox one");
        GamePlatformTypeVO GamePlatformTypeResult =
                gamePlatformTypeRepository.save(gamePlatformTypeVO);
        assertEquals("Xbox one",GamePlatformTypeResult.getGamePlatformName());
    }

    @Transactional
    @Test
    public void updateOneType() {
        GamePlatformTypeVO gamePlatformTypeQuery=
                gamePlatformTypeRepository.findById(64001).orElse(null);

        gamePlatformTypeQuery.setGamePlatformName("PS2");
        GamePlatformTypeVO gamePlatformTypeResult =
                gamePlatformTypeRepository.save(gamePlatformTypeQuery);

        assertNotNull(gamePlatformTypeResult);
        assertEquals("PS2",gamePlatformTypeResult.getGamePlatformName());
    }

    @Transactional
    @Test
    public void deleteOneType() {
        GamePlatformTypeVO gamePlatformTypeQuery=
                gamePlatformTypeRepository.findById(64001).orElse(null);

        gamePlatformTypeRepository.delete(gamePlatformTypeQuery);

        GamePlatformTypeVO gamePlatformTypeResult=
                gamePlatformTypeRepository.findById(64001).orElse(null);

        assertNull(gamePlatformTypeResult);
    }

}