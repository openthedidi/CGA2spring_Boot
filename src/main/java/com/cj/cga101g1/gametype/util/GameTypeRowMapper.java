package com.cj.cga101g1.gametype.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GameTypeRowMapper implements RowMapper<GameTypeVO> {
    @Override
    public GameTypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        GameTypeVO gameTypeVO = new GameTypeVO();
        gameTypeVO.setGameTypeNo(rs.getInt("GameTypeNo"));
        gameTypeVO.setGameTypeName(rs.getString("GameTypeName"));
        return gameTypeVO;
    }
}
