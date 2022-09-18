package com.cj.cga101g1.product.controller;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.service.Method01;
import com.cj.cga101g1.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

//@RequestMapping("CGA101G1/product")
///*** war檔用的網址 ****/
@RequestMapping("product")
@RestController
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/OneProductDetail")
    public ResponseEntity<Object> getProduct(@RequestParam Integer ProductNo){
        Object Object = productService.findByPrimaryKey(ProductNo);
       if(Object!=null){
           return ResponseEntity.status(HttpStatus.OK).body(Object);
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @GetMapping("/showSelledCount")
    public String showSelledCount(){
        String result = productService.showSelledCount();
        return result;
    }
    @GetMapping("/showPageProduct")
    public ResponseEntity<List<Object>> showPageProduct(@RequestParam (defaultValue = "0") String Page){
            Integer page = Integer.valueOf(Page);
            List<Object> list = productService.getAllSelledProductsByMap(page);
            return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showSellAndPlatFormTypeProductPages")
    public String showSellAndPlatFormTypeProductPages(@RequestParam Integer gamePlatformNo){
        String result = productService.showSelledCountByPlatFormType(gamePlatformNo);
        return result;
    }

    @GetMapping("/showSellAndPlatformProduct")
    public ResponseEntity<List<Object>> showSellAndPlatformProduct(@RequestParam Integer gamePlatformNo,
                                             @RequestParam Integer Page){
        List<Object> list = productService.showSellAndPlatformProduct(gamePlatformNo,Page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showSellAndGameTypeProductPages")
    public String showSellAndGameTypeProductPages(@RequestParam Integer gameTypeNo){
        String result = productService.showSellAndGameTypeProductPages(gameTypeNo);
        return result;
    }
    @GetMapping("/showSellAndGameTypeProduct")
    public ResponseEntity<List<Object>> showSellAndGameTypeProduct(@RequestParam Integer gameTypeNo,
                                                                   @RequestParam Integer Page){
        List<Object> list = productService.showSellAndGameTypeProduct(gameTypeNo,Page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showSellCountByMoney")
    public String showSellCountByMoney(@RequestParam Integer lowPrice,
                                       @RequestParam Integer highPrice){
        String result = productService.showSellCountByMoney(lowPrice,highPrice);
        return result;
    }



    //    @CrossOrigin(value = "http://localhost:8080")
    //    解決" 跨域資源共享”（Cross-origin resource sharing）的另一個方式
    @GetMapping("/showInSellByMapAndMoney")
    public ResponseEntity<List<Object>> showInSellByMapAndMoney(@RequestParam Integer lowPrice,
                                                                @RequestParam Integer highPrice,
                                                                   @RequestParam Integer Page){
        List<Object> list = productService.showInSellByMapAndMoney(lowPrice,highPrice,Page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showSellProductByKeyWordPages")
    public String showSellProductByKeyWordPages(@RequestParam String keyWord){
        String result = productService.showSellProductByKeyWordPages(keyWord);
        return result;
    }
    @GetMapping("/showSellProductByKeyWord")
    public ResponseEntity<List<Object>> showSellProductByKeyWord(@RequestParam String keyWord,
                                                                @RequestParam Integer Page){
        List<Object> list = productService.showSellProductByKeyWord(keyWord,Page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
