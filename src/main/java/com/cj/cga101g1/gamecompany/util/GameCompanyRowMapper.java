package com.cj.cga101g1.gamecompany.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class GameCompanyRowMapper implements RowMapper<GameCompany> {
    @Autowired
    private GameCompany gameCompany;
    @Override
    public GameCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
        gameCompany.setGameCompanyName(rs.getString("GameCompanyName"));
        gameCompany.setGameCompanyNo(rs.getInt("GameCompanyNo"));
        return gameCompany;
    }
}
