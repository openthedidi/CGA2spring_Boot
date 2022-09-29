package com.cj.cga101g1.gameplatformtype.util;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gameplatformtype")
@Component
public class GamePlatformTypeVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GamePlatformNo")
    private Integer gamePlatformNo;

    @NotNull
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
