package com.cj.cga101g1.gameNews.service;

import com.cj.cga101g1.gameNews.dao.GameNewsDao;
import com.cj.cga101g1.gameNews.dao.GameNewsDaoImp;
import com.cj.cga101g1.gameNews.dao.GameNewsRepository;
import com.cj.cga101g1.gameNews.model.GameNews;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameNewsService {

    @Autowired
    private GameNewsDaoImp gameNewsDao;

    public List<GameNews> findGameNewsByMultiKey(String gameNewsContent, String gameNewsTitle) {
        Specification<GameNews> spec = Specification.where(null);
        if (StringUtils.isNotEmpty(gameNewsContent)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("gameNewsContent"), "%" + gameNewsContent + "%"));
        }
        if (StringUtils.isNotEmpty(gameNewsTitle)) {
            spec = spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("gameNewsTitle"), "%" + gameNewsTitle + "%")));
        }
        Sort sort = Sort.by("gameNewsNo").descending();
        List<GameNews> result = gameNewsDao.findAll(spec, sort).orElse(null);
        if(result != null) {
            result.stream().map(
                    gameNews -> {
                        gameNews.setPicUrl("/CGA101G1/GameNews/getPicUrl?gameNewsNo=" + gameNews.getGameNewsNo());
                        gameNews.setGameNewsPic(null);
                        return gameNews;
                    }
            ).collect(Collectors.toList());
        }
        return result;
    }

    public byte[] getPicUrl(Integer gameNewsNo) {
        return gameNewsDao.getPicByID(gameNewsNo);
    }
}
