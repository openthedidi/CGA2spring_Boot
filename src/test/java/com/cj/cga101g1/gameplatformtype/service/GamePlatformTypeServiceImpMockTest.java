package com.cj.cga101g1.gameplatformtype.service;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GamePlatformTypeServiceImpMockTest {

    @Autowired
    private GamePlatformTypeService gamePlatformTypeService;

    /**
     * 模擬gamePlatformTypeDAO針對資料庫處理後的結果
     */
    @MockBean
    private GamePlatformTypeDAO gamePlatformTypeDAO;
    @Autowired
    private GamePlatformTypeVO gamePlatformTypeVO_Mock;

    @Test
    void getOneType() {
        gamePlatformTypeVO_Mock.setGamePlatformNo(64007);
        gamePlatformTypeVO_Mock.setGamePlatformName("PS3");

        Mockito.when(gamePlatformTypeDAO.getType(64007)).thenReturn(gamePlatformTypeVO_Mock);
//        Mockito.when(gamePlatformTypeDAO.getType(Mockito.anyInt()))
//                .thenThrow(new RuntimeException());
//        Mockito.verify(gamePlatformTypeDAO,Mockito.times(2)).getType(Mockito.anyInt());


        GamePlatformTypeVO gamePlatformTypeVO =
                gamePlatformTypeService.getOneType(64007);
        assertEquals("PS3",gamePlatformTypeVO.getGamePlatformName());
        assertEquals(64007,gamePlatformTypeVO.getGamePlatformNo());

        GamePlatformTypeVO failResult =
                gamePlatformTypeService.getOneType(64001);
        assertNull(failResult);
    }

    @Test
    void newPlatformType() throws SQLException {
        Mockito.when(gamePlatformTypeDAO.newType(gamePlatformTypeVO_Mock)).thenReturn(gamePlatformTypeVO_Mock);
        GamePlatformTypeVO gamePlatformTypeVOIn = new GamePlatformTypeVO();
        gamePlatformTypeVOIn.setGamePlatformNo(556);
        gamePlatformTypeVOIn.setGamePlatformName("Mock");
        GamePlatformTypeVO gamePlatformTypeResult =
                gamePlatformTypeService.newPlatformType(gamePlatformTypeVOIn);
        assertEquals(556,gamePlatformTypeResult.getGamePlatformNo());
        assertEquals("Mock",gamePlatformTypeResult.getGamePlatformName());

    }

    @Test
    void getOneTypeByName() {
        gamePlatformTypeVO_Mock.setGamePlatformNo(64001);
        gamePlatformTypeVO_Mock.setGamePlatformName("PS4");
        Mockito.when(gamePlatformTypeDAO.getType("PS4")).thenReturn(gamePlatformTypeVO_Mock);
        GamePlatformTypeVO NullResult = gamePlatformTypeService.getOneTypeByName("SWitch");
        GamePlatformTypeVO GoodResult = gamePlatformTypeService.getOneTypeByName("PS4");
        assertNull(NullResult);
        assertNotNull(GoodResult);
        assertEquals("PS4",GoodResult.getGamePlatformName());
    }

    @Test
    void getOneTypeByKeyName() {
        List<GamePlatformTypeVO> MockList = new ArrayList<>();
        gamePlatformTypeVO_Mock.setGamePlatformNo(64002);
        gamePlatformTypeVO_Mock.setGamePlatformName("SWitch");
        MockList.add(gamePlatformTypeVO_Mock);
        Mockito.when(gamePlatformTypeDAO.getTypeByKeyword("S")).thenReturn(MockList);
        List<GamePlatformTypeVO> MockResultList = gamePlatformTypeService.getOneTypeByKeyName("S");
        assertNotNull(MockResultList);
        assertEquals(1,MockResultList.size());
        assertEquals(64002,MockResultList.get(0).getGamePlatformNo());

        List<GamePlatformTypeVO> failResultList = gamePlatformTypeService.getOneTypeByKeyName("P");
        assertEquals(0,failResultList.size());

    }

    @Test
    void updateOneType() {
        gamePlatformTypeVO_Mock.setGamePlatformNo(64001);
        gamePlatformTypeVO_Mock.setGamePlatformName("PS4");
        Mockito.when(gamePlatformTypeDAO.updateOneType(gamePlatformTypeVO_Mock)).thenReturn(gamePlatformTypeVO_Mock);
        GamePlatformTypeVO gamePlatformTypeVO = new GamePlatformTypeVO();
        gamePlatformTypeVO.setGamePlatformNo(64002);
        gamePlatformTypeVO.setGamePlatformName("PS3");
        GamePlatformTypeVO NullResult = gamePlatformTypeService.updateOneType(gamePlatformTypeVO);
        GamePlatformTypeVO GoodResult = gamePlatformTypeService.updateOneType(gamePlatformTypeVO_Mock);
        assertNull(NullResult);
        assertNotNull(GoodResult);
        assertEquals("PS4",GoodResult.getGamePlatformName());
    }

    @Test
    void deleteOneType() {
        gamePlatformTypeVO_Mock.setGamePlatformNo(64001);
        gamePlatformTypeVO_Mock.setGamePlatformName("PS4");
        Mockito.when(gamePlatformTypeDAO.deleteOneType(gamePlatformTypeVO_Mock)).thenReturn("delete success");

        String result = gamePlatformTypeService.deleteOneType(gamePlatformTypeVO_Mock);
        assertEquals("delete success",result);

        GamePlatformTypeVO failVO = new GamePlatformTypeVO();
        failVO.setGamePlatformNo(64002);
        failVO.setGamePlatformName("PS3");
        String failResult = gamePlatformTypeService.deleteOneType(failVO);
        assertNull(failResult);
    }
}