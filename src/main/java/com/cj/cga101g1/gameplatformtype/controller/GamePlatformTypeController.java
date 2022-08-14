package com.cj.cga101g1.gameplatformtype.controller;



import com.cj.cga101g1.gameplatformtype.service.GamePlatformTypeService;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/gamePlatformType")
public class GamePlatformTypeController {

    @Autowired
    private GamePlatformTypeService gamePlatformTypeService;


    @PostMapping("/newType")
    public GamePlatformTypeVO newType(@RequestBody GamePlatformTypeVO gamePlatformTypeVO){
        return gamePlatformTypeService.newPlatformType(gamePlatformTypeVO);
    }

    @GetMapping("/getOneType/{gamePlatformNo}")
    public GamePlatformTypeVO getOneType(@PathVariable Integer gamePlatformNo){
        return gamePlatformTypeService.getOneType(gamePlatformNo);
    }

    @GetMapping("/getOneTypeByName/{gamePlatformName}")
    public GamePlatformTypeVO getOneTypeByName(@PathVariable String gamePlatformName){
        return gamePlatformTypeService.getOneTypeByName(gamePlatformName);
    }


    @GetMapping("/getOneTypeByKeyName/{KeyName}")
    public List<GamePlatformTypeVO> getOneTypeByKeyName(@PathVariable String KeyName){
        return gamePlatformTypeService.getOneTypeByKeyName(KeyName);
    }


    @PutMapping("/updateOneType/{gamePlatformNo}")
    public GamePlatformTypeVO updateOneType(@PathVariable Integer gamePlatformNo,
                                            @RequestBody GamePlatformTypeVO gamePlatformTypeVO){

        gamePlatformTypeVO.setGamePlatformNo(gamePlatformNo);
        /*****  先進行查詢看是否存在DB上，如果DB沒有也不能新增的動作  ****/
        GamePlatformTypeVO gamePlatformTypeVOQuery = gamePlatformTypeService.getOneType(gamePlatformNo);
        if(gamePlatformTypeVOQuery.getGamePlatformNo()!=000) {
            return gamePlatformTypeService.updateOneType(gamePlatformTypeVO);
        }else{
            return gamePlatformTypeVOQuery;
        }
    }

    @DeleteMapping("/deleteType/{gamePlatformNo}")
    public String deleteType(@PathVariable Integer gamePlatformNo){

        GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeService.getOneType(gamePlatformNo);
        if(gamePlatformTypeVO.getGamePlatformNo()!=000){
        return gamePlatformTypeService.deleteOneType(gamePlatformTypeService.getOneType(gamePlatformNo));
        }else {
            return "DB無此ID："+gamePlatformNo+"的種類";
        }
    }

}
