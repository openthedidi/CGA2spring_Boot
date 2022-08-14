package com.cj.cga101g1.gametype.dao;

import com.cj.cga101g1.gametype.util.GameTypeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameTypeDAOImpTest {

    @Autowired
    private GameTypeDAO gameTypeDAO;

    @Test
    public void getGameType(){
        GameTypeVO gameTypeVO =gameTypeDAO.getGameType(63001);
        assertNotNull(gameTypeVO);
        assertEquals("動作",gameTypeVO.getGameTypeName());
    }



}