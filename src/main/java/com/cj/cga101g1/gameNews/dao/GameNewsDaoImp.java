package com.cj.cga101g1.gameNews.dao;

import com.cj.cga101g1.gameNews.model.GameNews;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class GameNewsDaoImp {


    public static Specification<GameNews> byGamePlatformNo(Integer gamePlatformNo) {
        return (Root<GameNews> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("gamePlatformNo"), gamePlatformNo);
    }
}
