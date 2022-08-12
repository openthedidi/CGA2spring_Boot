package com.cj.cga101g1.gameplatformtype.util;


import javax.persistence.*;

@Entity
@Table(name = "gameplatformtype")
public class GamePlatformTypeVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GamePlatformNo")
    private Integer gamePlatformNo;

    @Column(name = "GamePlatformName")
    private String gamePlatformName;

    public Integer getGamePlatformNo() {
        return gamePlatformNo;
    }

    public void setGamePlatformNo(Integer gamePlatformNo) {
        this.gamePlatformNo = gamePlatformNo;
    }

    public String getGamePlatformName() {
        return gamePlatformName;
    }

    public void setGamePlatformName(String gamePlatformName) {
        this.gamePlatformName = gamePlatformName;
    }
}
