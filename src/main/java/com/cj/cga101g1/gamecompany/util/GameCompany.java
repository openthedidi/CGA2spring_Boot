package com.cj.cga101g1.gamecompany.util;

import org.springframework.stereotype.Component;

@Component
public class GameCompany {
    private Integer gameCompanyNo;
    private String gameCompanyName;

    public Integer getGameCompanyNo() {
        return gameCompanyNo;
    }

    public void setGameCompanyNo(Integer gameCompanyNo) {
        this.gameCompanyNo = gameCompanyNo;
    }

    public String getGameCompanyName() {
        return gameCompanyName;
    }

    public void setGameCompanyName(String gameCompanyName) {
        this.gameCompanyName = gameCompanyName;
    }
}
