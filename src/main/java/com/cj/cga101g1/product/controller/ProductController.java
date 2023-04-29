package com.cj.cga101g1.product.controller;

import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@RequestMapping("CGA101G1/product")
///*** war檔用的網址 ****/
@RequestMapping("CGA101G1/product")
@RestController
@Validated
@Tag(name = "product api")
public class ProductController {

    @Autowired
    @Qualifier("ProductServiceImp")
    private ProductService productService;

    @Autowired
    @Qualifier("ProductServiceImpRedisVer")
    private ProductService productServiceImpRedisVer;

    @GetMapping("/OneProductDetail")
    @Operation(summary = "單一產品詳細資訊")
    public ResponseEntity<Object> getProduct(@RequestParam Integer ProductNo){
        Object Object = productService.findByPrimaryKey(ProductNo);
       if(Object!=null){
           return ResponseEntity.status(HttpStatus.OK).body(Object);
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @GetMapping("/showSelledCount")
    @Operation(summary = "上架數量")
    public String showSelledCount(){
        String result = productService.showSelledCount();
        return result;
    }
    @GetMapping("/showPageProduct")
    @Operation(summary = "取頁數產品")
    public ResponseEntity<List<Object>> showPageProduct(@RequestParam (defaultValue = "0", name = "頁數") String Page){
            Integer page = Integer.valueOf(Page);
            List<Object> list = productService.getAllSelledProductsByMap(page);
            return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showPageProduct/redis")
    public ResponseEntity<List<Object>> showPageProductByRedis(@Parameter(description = "頁數") @RequestParam (defaultValue = "0") String Page){
        Integer page = Integer.valueOf(Page);
        List<Object> list = productServiceImpRedisVer.getAllSelledProductsByMap(page);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/showSellAndPlatFormTypeProductPages")
    @Operation(summary = "取特定平台的頁數",responses = {
            @ApiResponse(responseCode = "200", description = "回傳特定平台的總pages"),
            @ApiResponse(responseCode = "404", description = "pages not found")
    })
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

    @Transactional
    @PostMapping("/newProduct")
    public ResponseEntity<Product> newProduct(@RequestBody @Valid Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/showCart")
    public ResponseEntity<List<CartDetail>> showCart(HttpSession session){
        List<CartDetail> cartDetailList = ((List<CartDetail>) session.getAttribute("shoppingCart") == null
                ? new ArrayList<CartDetail>()
                : (List<CartDetail>) session.getAttribute("shoppingCart"));
        return ResponseEntity.ok(cartDetailList);
    }

    @GetMapping("/showAllProductsName")
    public ResponseEntity<List> showAllProductsName(){
;
        return ResponseEntity.ok(productService.showAllProductsName());
    }


}
