package com.cj.cga101g1.gameNews.dao;

import com.cj.cga101g1.gameNews.model.GameNews;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameNewsRepository extends JpaRepository<GameNews, Integer> {

    List<GameNews> findAll(Specification<GameNews> spec, Sort sort);

    @Query(value = "select gameNewsPic from GameNews where gameNewsNo = :gameNewsNo" , nativeQuery = true)
    byte[] getPicByID(Integer gameNewsNo);
}
