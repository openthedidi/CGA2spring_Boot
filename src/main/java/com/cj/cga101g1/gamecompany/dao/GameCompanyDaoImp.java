package com.cj.cga101g1.gamecompany.dao;

import com.cj.cga101g1.gamecompany.util.GameCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameCompanyDaoImp implements GameCompanyDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private GameCompanyRowMapper gameCompanyRowMapper;
    @Autowired
    private GameCompany gameCompany;

    @Override
    public GameCompany findByNo(Integer gameCompanyNo) {
        final String sql = "select GameCompanyNo,GameCompanyName from gamecompany where GameCompanyNo = :GameCompanyNo;";
        Map<String,Object>map = new HashMap<>();
        map.put("GameCompanyNo",gameCompanyNo);
        gameCompany = namedParameterJdbcTemplate.query(sql,map,gameCompanyRowMapper).get(0);
        return gameCompany;
    }
}
