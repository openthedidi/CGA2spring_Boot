package com.cj.cga101g1.gameplatformtype.dao;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import com.cj.cga101g1.util.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GamePlatformTypeDAOImp implements GamePlatformTypeDAO{


    @Autowired
    private GamePlatformTypeRepository gamePlatformTypeRepository;

    @Autowired
    private GamePlatformTypeVO gamePlatformTypeVO;

    public GamePlatformTypeVO newType(GamePlatformTypeVO gamePlatformTypeVO) throws RuntimeException, SQLException {
        if(gamePlatformTypeVO.getGamePlatformName()==null){
            throw new SQLException("填入參數有問題");
        }
        if(gamePlatformTypeRepository.save(gamePlatformTypeVO)==null){
            throw new NullPointerException("無法新增");
        }
        return gamePlatformTypeRepository.save(gamePlatformTypeVO);
    }

    @Override
    public GamePlatformTypeVO updateOneType(GamePlatformTypeVO gamePlatformTypeVO) {
        return gamePlatformTypeRepository.save(gamePlatformTypeVO);
    }

    @Override
    public String deleteOneType(GamePlatformTypeVO gamePlatformTypeVO) {
        gamePlatformTypeRepository.delete(gamePlatformTypeVO);
        return "成功刪除id為"+gamePlatformTypeVO.getGamePlatformNo()+"的遊戲種類";
    }

    @Override
    public GamePlatformTypeVO getType(Integer gamePlatformTypeNo) throws ResourceNotFoundException {
        Optional<GamePlatformTypeVO> optionalGamePlatformTypeVO=gamePlatformTypeRepository.findById(gamePlatformTypeNo);
        if(optionalGamePlatformTypeVO.isPresent()){
            return optionalGamePlatformTypeVO.get();
        }else{
            throw new ResourceNotFoundException("找不到指定的對象");
        }
    }

    @Override
    public GamePlatformTypeVO getType(String gamePlatformTypeName) {
        return gamePlatformTypeRepository.FindByGamePlatformName(gamePlatformTypeName);
    }

    @Override
    public List<GamePlatformTypeVO> getTypeByKeyword(String keyword) {
        return gamePlatformTypeRepository.FindByKeywordName(keyword);
    }

    @Override
    public List<GamePlatformTypeVO> getAllType() {
        Iterable <GamePlatformTypeVO> iterable = gamePlatformTypeRepository.findAll();
        List<GamePlatformTypeVO> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

}
