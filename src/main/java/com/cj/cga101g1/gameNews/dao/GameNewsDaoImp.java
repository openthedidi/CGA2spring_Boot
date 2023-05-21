package com.cj.cga101g1.gameNews.dao;

import com.cj.cga101g1.gameNews.model.GameNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GameNewsDaoImp implements GameNewsDao{

    @Autowired
    private GameNewsRepository gameNewsRepository;

    @Override
    public Optional<List<GameNews>> findAll(Specification<GameNews> spec, Sort sort) {
        List<GameNews> result = gameNewsRepository.findAll(spec, sort);
        Optional<List<GameNews>> optionalGameNews = Optional.ofNullable(result);
        return optionalGameNews;
    }

    @Override
    public byte[] getPicByID(Integer gameNewsNo) {
        return gameNewsRepository.getPicByID(gameNewsNo);
    }
}
