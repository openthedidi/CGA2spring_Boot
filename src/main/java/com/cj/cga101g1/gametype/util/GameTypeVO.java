package com.cj.cga101g1.gametype.util;


import org.springframework.stereotype.Component;



@Component
public class GameTypeVO {
    private String gameTypeName;
    private  Integer gameTypeNo;

    public String getGameTypeName() {
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }

    public Integer getGameTypeNo() {
        return gameTypeNo;
    }

    public void setGameTypeNo(Integer gameTypeNo) {
        this.gameTypeNo = gameTypeNo;
    }
}
