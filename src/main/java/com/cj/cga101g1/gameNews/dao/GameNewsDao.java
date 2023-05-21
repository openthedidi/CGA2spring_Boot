package com.cj.cga101g1.gameNews.dao;

import com.cj.cga101g1.gameNews.model.GameNews;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface GameNewsDao {

    Optional<List<GameNews>> findAll(Specification<GameNews> spec, Sort sort);

    byte[] getPicByID(Integer gameNewsNo);
}
