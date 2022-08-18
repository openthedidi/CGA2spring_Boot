package com.cj.cga101g1.gameplatformtype.controller;



import com.cj.cga101g1.gameplatformtype.service.GamePlatformTypeService;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("CGA101G1/gameplatformtype")
public class GamePlatformTypeController {

    @Autowired
    private GamePlatformTypeService gamePlatformTypeService;


    @PostMapping("/newType")
    public GamePlatformTypeVO newType(@RequestBody GamePlatformTypeVO gamePlatformTypeVO){
        return gamePlatformTypeService.newPlatformType(gamePlatformTypeVO);
    }

    @GetMapping("/getAllGamePlatformType")
    public ResponseEntity<List<GamePlatformTypeVO>> getAllType(){
        List<GamePlatformTypeVO> list=  gamePlatformTypeService.getAllType();
        return ResponseEntity.status(HttpStatus.OK).body(list);
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

    /** 測試利用resttemplate發請求 **/
    @GetMapping("/useResttemplate")
    public String useResttemplate(){
        RestTemplate restTemplate = new RestTemplate();
        GamePlatformTypeVO gamePlatformTypeVO =
                restTemplate.getForObject("https://mocki.io/v1/476013b9-4c92-41e8-8e08-08b3ffb28447",GamePlatformTypeVO.class);
        return gamePlatformTypeVO.getGamePlatformName();
    }

}
