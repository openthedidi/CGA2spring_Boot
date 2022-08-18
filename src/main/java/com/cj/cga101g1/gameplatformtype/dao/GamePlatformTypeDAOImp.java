package com.cj.cga101g1.gameplatformtype.dao;

import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GamePlatformTypeDAOImp implements GamePlatformTypeDAO{


    @Autowired
    private GamePlatformTypeRepository gamePlatformTypeRepository;

    @Autowired
    private GamePlatformTypeVO gamePlatformTypeVO;

    public GamePlatformTypeVO newType(GamePlatformTypeVO gamePlatformTypeVO){
        gamePlatformTypeRepository.save(gamePlatformTypeVO);
        return gamePlatformTypeVO;
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
    public GamePlatformTypeVO getType(Integer gamePlatformTypeNo) {
        Optional<GamePlatformTypeVO> optionalGamePlatformTypeVO=gamePlatformTypeRepository.findById(gamePlatformTypeNo);
        if(optionalGamePlatformTypeVO.isPresent()){
            return optionalGamePlatformTypeVO.get();
        }else{
            gamePlatformTypeVO.setGamePlatformNo(000);
            gamePlatformTypeVO.setGamePlatformName("查無此項");
            return gamePlatformTypeVO;
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
