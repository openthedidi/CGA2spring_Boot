package com.cj.cga101g1.product.model;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ProductResultSetExtractor implements ResultSetExtractor<Object> {
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()){
           return rs.getInt("count(productNo)");
        }else{
            return null;
        }
    }
}
