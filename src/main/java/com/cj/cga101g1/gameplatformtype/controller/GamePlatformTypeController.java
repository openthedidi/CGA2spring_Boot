package com.cj.cga101g1.gameplatformtype.controller;



import com.cj.cga101g1.gameplatformtype.service.GamePlatformTypeService;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import com.cj.cga101g1.util.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("gameplatformtype")
public class GamePlatformTypeController {

    @Autowired
    @Qualifier("GamePlatformTypeServiceImp")
    private GamePlatformTypeService gamePlatformTypeService;

    @Autowired
    @Qualifier("GamePlatformTypeServiceImpRedisVer")
    private GamePlatformTypeService GamePlatformTypeServiceImpRedisVer;


    @Autowired
    private GamePlatformTypeVO gamePlatformTypeVO;

    @PostMapping("/newType")
    public GamePlatformTypeVO newType(@RequestBody @Valid GamePlatformTypeVO gamePlatformTypeVO) throws SQLException {
        return gamePlatformTypeService.newPlatformType(gamePlatformTypeVO);

//        try {
//            return gamePlatformTypeService.newPlatformType(gamePlatformTypeVO);
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }catch (NullPointerException nullPointerException){
//            throw new RuntimeException("參數有問題： "
//                    + nullPointerException.getMessage());
//        }finally {
//            this.gamePlatformTypeVO.setGamePlatformName("請聯絡管理員");
//            return this.gamePlatformTypeVO;
//        }
    }

    @GetMapping("/getAllGamePlatformType")
    public ResponseEntity<List<GamePlatformTypeVO>> getAllType(){
        List<GamePlatformTypeVO> list=  gamePlatformTypeService.getAllType();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/getOneType/{gamePlatformNo}")
    public ResponseEntity<GamePlatformTypeVO> getOneType(@PathVariable Integer gamePlatformNo){
        gamePlatformTypeVO=gamePlatformTypeService.getOneType(gamePlatformNo);
            return ResponseEntity.status(200).body(gamePlatformTypeVO);

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
    public ResponseEntity<?> deleteType(@PathVariable Integer gamePlatformNo){
        GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeService.getOneType(gamePlatformNo);
        if(gamePlatformTypeVO.getGamePlatformNo()!=000){
        return ResponseEntity.status(HttpStatus.OK).body(null);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
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

    @GetMapping("/config")
    public ResponseEntity<?> configProperties(){
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        propertiesConfig.setUrl("jdbc:mysql://127.0.0.1:3306/cga101g1?serverTimezone=Asia/Taipei&characterEncoding=utf-8");
        propertiesConfig.setUsername("openthedidi");
        propertiesConfig.setPassword("bird");
        return ResponseEntity.status(200).body(gamePlatformTypeService.getOneType(64003));
    }



    @GetMapping("/getOneTypeByKeyName/redis/{KeyName}")
    public List<GamePlatformTypeVO> getOneTypeByKeyNameRedis(@PathVariable String KeyName){
        return GamePlatformTypeServiceImpRedisVer.getOneTypeByKeyName(KeyName);
    }

    @PutMapping("/updateOneType/redis/{gamePlatformNo}")
    public GamePlatformTypeVO updateOneTypeByRedis(@PathVariable Integer gamePlatformNo,
                                            @RequestBody GamePlatformTypeVO gamePlatformTypeVO){

        gamePlatformTypeVO.setGamePlatformNo(gamePlatformNo);
        /*****  先進行查詢看是否存在DB上，如果DB沒有也不能新增的動作  ****/
        GamePlatformTypeVO gamePlatformTypeVOQuery = GamePlatformTypeServiceImpRedisVer.getOneType(gamePlatformNo);
        if(gamePlatformTypeVOQuery.getGamePlatformNo()!=000) {
            return GamePlatformTypeServiceImpRedisVer.updateOneType(gamePlatformTypeVO);
        }else{
            return gamePlatformTypeVOQuery;
        }
    }

    @GetMapping("/getAllGamePlatformType/redis")
    public ResponseEntity<List<GamePlatformTypeVO>> getAllTypeByRedis(){
        List<GamePlatformTypeVO> list=  GamePlatformTypeServiceImpRedisVer.getAllType();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
