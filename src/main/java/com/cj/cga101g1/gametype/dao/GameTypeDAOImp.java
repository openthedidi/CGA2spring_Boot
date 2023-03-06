package com.cj.cga101g1.gametype.dao;

import com.cj.cga101g1.gametype.util.GameTypeVO;
import com.cj.cga101g1.gametype.util.GameTypeResultSetExtractor;
import com.cj.cga101g1.gametype.util.GameTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameTypeDAOImp implements GameTypeDAO{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private GameTypeVO gameTypeVO;
    @Autowired
    private GameTypeRowMapper gameTypeRowMapper;
    @Autowired
    private GameTypeResultSetExtractor gameTypeResultSetExtractor;

    @Override
    public GameTypeVO newGameType(String gameTypeName) {
        final String sql = "insert into cga101g1.gametype (GameTypeName) values (:GameTypeName)";
        Map<String,Object> map = new HashMap<>();
        map.put("GameTypeName",gameTypeName);

        /************   查詢auto_increment的GameTypeNo   **************/
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int number = keyHolder.getKey().intValue();
        gameTypeVO.setGameTypeName(gameTypeName);
        gameTypeVO.setGameTypeNo(number);
        return gameTypeVO;
    }

    @Override
    public List<GameTypeVO> newGameTypes(List<GameTypeVO> list) {
        final String sql = "insert into cga101g1.gametype (GameTypeName) values (:GameTypeName)";
        MapSqlParameterSource mapSqlParameterSource[]= new MapSqlParameterSource[list.size()];
        for (int i = 0; i< mapSqlParameterSource.length;i++){
            gameTypeVO =(list.get(i));
            mapSqlParameterSource[i]=new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("GameTypeName",gameTypeVO.getGameTypeName());
        }
        int[] result = namedParameterJdbcTemplate.batchUpdate(sql,mapSqlParameterSource);
        return list;
    }

    @Override
    public List<GameTypeVO> getGameTypes() {
        final String sql = "select GameTypeNo,GameTypeName from cga101g1.gametype";
        Map<String,Object> map = new HashMap<>();
        List<GameTypeVO> list = namedParameterJdbcTemplate.query(sql,map,gameTypeRowMapper);
        return  list;
    }

    @Override
    public List<GameTypeVO> getGameTypesByRs() {
        final String sql = "select GameTypeNo,GameTypeName from cga101g1.gametype";
        Map<String,Object> map = new HashMap<>();
        List<GameTypeVO> list = namedParameterJdbcTemplate.query(sql,map,gameTypeResultSetExtractor);
        return  list;
    }

    @Override
    public GameTypeVO getGameType(Integer gameTypeNo) {
        final String sql = "select GameTypeNo,GameTypeName from cga101g1.gametype where GameTypeNo = :GameTypeNo;";
        Map<String,Object> map = new HashMap<>();
        map.put("GameTypeNo",gameTypeNo);
        List<GameTypeVO> list = namedParameterJdbcTemplate.query(sql,map,gameTypeRowMapper);
        if(list.size()>0){
            return  list.get(0);
        }else{
            return  null;
        }
    }
}
