package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GamePlatformTypeServiceImpMockTest {

    @Autowired
    private GamePlatformTypeService gamePlatformTypeService;
    @MockBean
    private GamePlatformTypeDAO gamePlatformTypeDAO;

    @Test
    void getOneType() {
        GamePlatformTypeVO gamePlatformTypeVO_Mock = new GamePlatformTypeVO();
        gamePlatformTypeVO_Mock.setGamePlatformNo(64007);
        gamePlatformTypeVO_Mock.setGamePlatformName("PS3");

        Mockito.when(gamePlatformTypeDAO.getType(Mockito.anyInt())).thenReturn(gamePlatformTypeVO_Mock);
//        Mockito.when(gamePlatformTypeDAO.getType(Mockito.anyInt()))
//                .thenThrow(new RuntimeException());
//        Mockito.verify(gamePlatformTypeDAO,Mockito.times(2)).getType(Mockito.anyInt());

        GamePlatformTypeVO gamePlatformTypeVO =
                gamePlatformTypeService.getOneType(64002);
        assertEquals("PS3",gamePlatformTypeVO.getGamePlatformName());
        assertEquals(64007,gamePlatformTypeVO.getGamePlatformNo());
    }

    @Test
    void newPlatformType() {
        GamePlatformTypeVO gamePlatformTypeVO_Mock = new GamePlatformTypeVO();
        gamePlatformTypeVO_Mock.setGamePlatformName("Mock");
        gamePlatformTypeVO_Mock.setGamePlatformNo(556688);
        Mockito.when(gamePlatformTypeDAO.newType(Mockito.any(GamePlatformTypeVO.class))).thenReturn(gamePlatformTypeVO_Mock);
        GamePlatformTypeVO gamePlatformTypeVOIn = new GamePlatformTypeVO();
        gamePlatformTypeVOIn.setGamePlatformNo(5588);
        gamePlatformTypeVOIn.setGamePlatformName("NoMock");
        GamePlatformTypeVO gamePlatformTypeResult =
                gamePlatformTypeService.newPlatformType(gamePlatformTypeVOIn);
        assertEquals("556688",gamePlatformTypeResult.getGamePlatformNo());

    }

    @Test
    void getOneTypeByName() {
    }

    @Test
    void getOneTypeByKeyName() {
    }

    @Test
    void updateOneType() {
    }

    @Test
    void deleteOneType() {
    }
}