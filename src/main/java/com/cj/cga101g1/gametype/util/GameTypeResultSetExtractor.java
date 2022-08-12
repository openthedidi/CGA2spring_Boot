package com.cj.cga101g1.gametype.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameTypeResultSetExtractor implements ResultSetExtractor<List<GameTypeVO>> {
    @Override
    public List<GameTypeVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GameTypeVO> list = new ArrayList<>();
        while (rs.next()){
            GameTypeVO gameTypeVO = new GameTypeVO();
            gameTypeVO.setGameTypeName(rs.getString("GameTypeName"));
            gameTypeVO.setGameTypeNo(rs.getInt("GameTypeNo"));
            list.add(gameTypeVO);
        }
        return list;
    }
}
