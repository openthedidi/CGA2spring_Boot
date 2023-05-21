package com.cj.cga101g1.util.specUtil;

import org.springframework.data.jpa.domain.Specification;

public abstract class SpecUtil {


    /**
     *  設定where 加上
     *  and column = columnValue
     * */
    public Specification<?> setAndEquals(Specification<?> spec, String... columnNames){
        for (String columnName: columnNames) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(columnName), columnName));
        }
        return spec;
    }


    /**
     *  設定where 加上
     *  or column = columnValue
     * */
    public Specification<?> setOrEquals(Specification<?> spec, String... columnNames){
        for (String columnName: columnNames) {
            spec = spec.or((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(columnName), columnName));
        }
        return spec;
    }
}
