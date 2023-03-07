package com.cj.cga101g1.member.controller;


import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.member.util.Mem;
import com.cj.cga101g1.util.jwt.JwtTokenUtils;
import com.cj.cga101g1.util.jwt.JwtUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.Map;


@RequestMapping("mem")
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtUserSecurityService jwtUserSecurityService;


    @PostMapping("/LoginServlet")
    public ResponseEntity<Mem> login(@RequestBody Mem mem, HttpSession session) {
        Mem memResult = new Mem();
        if (memberService.getMemByMemAccount(mem.getMemAccount()) == null) {
            memResult.setMessage("查無此會員");
            memResult.setSuccessful(false);
            return ResponseEntity.status(400).body(memResult);
        }

        memResult = memberService.login(mem);
        //如果帳密錯誤即立刻回傳
        if ("帳號或密碼錯誤".equals(memResult.getMessage())) {
            return ResponseEntity.status(404).body(mem);
        }
        //如果尚未驗證即立刻回傳
        if (memResult.getMemVrfed() == 0) {
            memResult.setMessage("尚未驗證，請至信箱點取驗證超連結");
            memResult.setSuccessful(false);
            return ResponseEntity.status(401).body(memResult);
        }
        //帳號遭停權則回傳
        if (memResult.getMemStatus() == 0) {
            memResult.setMessage("帳號已被停權，如有疑問請洽詢客服");
            memResult.setSuccessful(false);
            return ResponseEntity.status(403).body(memResult);
        }
        //驗證成功設定session
        if (memResult.isSuccessful()) {
            if (session.getAttribute("memVo") != null) {
                session.getId();
            }
            session.setAttribute("loggedin", true);
            session.setAttribute("memVO", memResult);
        }

        String init = (String) session.getAttribute("initlocationMem");
        if (init != null) {
            session.removeAttribute("initlocationMem");
            memResult.setInitlocation(init);
        }
        System.out.println(session.getId());
        return ResponseEntity.status(200).body(memResult);
    }

    @PostMapping("/jwt/login")
    public ResponseEntity<String> loginByJWT(@RequestBody Mem mem) {
        Mem memResult;
        try {
            memResult = (Mem)  jwtUserSecurityService.loadUserByUserPassword(mem.getMemAccount(),mem.getMemPassword());
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
        JwtTokenUtils jwtToken = new JwtTokenUtils();
        String token = jwtToken.createToken(memResult.getUsername(), "customer", false); // 取得token
        return ResponseEntity.ok(token);
    }


    @GetMapping("/logoutServlet")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().removeAttribute("memVO");
//        String url = "/frontend/memLogin/login.html";
        String url = "https://www.google.com/";
        RequestDispatcher successView = request.getRequestDispatcher(url);
        successView.forward(request, response);
    }




    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Mem mem) {
        Mem memResult = memberService.newMember(mem);
        if (memResult != null) {
            JwtTokenUtils jwtToken = new JwtTokenUtils();
            String token = jwtToken.createToken(memResult.getUsername(), "customer", false); // 取得token
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("註冊失敗");
        }
    }

    @GetMapping("/getMemByMemEmail")
    public ResponseEntity<Mem> getMemByMemEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemByMemEmail(email));
    }

    @PostMapping("/MemSelfInfo")
    public ResponseEntity<Mem> getMemSelfInfo(HttpSession session) {
        Mem mem = (Mem) session.getAttribute("memVO");
        System.out.println("目前session的會員號碼是： " + mem.getMemNo());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemSelfInfo((Mem) session.getAttribute("memVO")));
    }

    /**
     * 先從Request的Header中取kye為Authorization之value的token
     * 再進行驗證。
     */
    @PostMapping("/jwt/MemSelfInfo")
    public ResponseEntity<Mem> getMemSelfInfoByJwt(@RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtToken = new JwtTokenUtils();
        if (jwtToken.validateToken(token)) {
            String memAccount = jwtToken.getUsername(token);
            Mem mem = memberService.getMemSelfInfo(memberService.getMemByMemAccount(memAccount));
            mem.setMessage("成功");
            mem.setSuccessful(true);
            return ResponseEntity.status(HttpStatus.OK).body(mem);
        } else {
            Mem mem = new Mem();
            mem.setSuccessful(false);
            mem.setMessage("未獲得許可");
            System.out.println("驗正失敗");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mem);
        }
    }

    @GetMapping(value = "/MemSelfPicServlet", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody
    byte[] showMemSelfPic(@RequestParam Integer memNo) {
        System.out.println("showMemSelfPic");
        return memberService.showMemSelfPic(memNo);
    }


    @PutMapping("/memEdit/{memNo}")
    public ResponseEntity<Mem> memEdit(@RequestBody @Valid Mem mem,
                                       @PathVariable Integer memNo,
                                       @RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtToken = new JwtTokenUtils();
        if (jwtToken.validateToken(token)) {
            System.out.println("token 驗證通過");
            Mem memResult = memberService.memEdit(memNo, mem);
            return ResponseEntity.status(HttpStatus.OK).body(memResult);
        } else {
            mem.setSuccessful(false);
            mem.setMessage("未獲得許可");
            System.out.println("驗正失敗");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mem);
        }
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/memSessionTest")
    public void memSessionTest(HttpSession session) {
        System.out.println(session.getId());
    }

    @GetMapping("/checkMemberAccount/{account}")
    public ResponseEntity<Mem> checkMemberAccount(@PathVariable String account) {
        Mem mem = memberService.getMemByMemAccount(account);
        if(mem == null){
            Mem mem2 = new Mem();
            mem2.setMessage("可以註冊");
            return ResponseEntity.status(HttpStatus.OK).body(mem2);
        }
        mem.setMessage("帳號名稱重複");
        mem.setMemPassword("");
        mem.setMemEmail("");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mem);
    }

    @GetMapping("/checkMemberEmail/{email}")
    public ResponseEntity<Mem> checkMemberEmail(@PathVariable String email) {
        Mem mem = memberService.getMemByMemAEmail(email);
        if(mem == null){
            Mem mem2 = new Mem();
            mem2.setMessage("可以註冊");
            return ResponseEntity.status(HttpStatus.OK).body(mem2);
        }
        mem.setMessage("信箱重複");
        mem.setMemPassword("");
        mem.setMemEmail("");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mem);
    }

    @GetMapping(value = "/getMemPic",produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] getMemPic(@RequestParam Integer memNo){
        return memberService.showMemSelfPic(memNo);
    }

    @GetMapping("/showShoppingMemInfo")
    public ResponseEntity<Map> showShoppingMemInfo(@RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        System.out.println("jwtTokenUtils.validateToken : " + jwtTokenUtils.validateToken(token));
        if(jwtTokenUtils.validateToken(token)){
            String memAccount = jwtTokenUtils.getUsername(token);
            System.out.println(memAccount);

            return ResponseEntity.ok(memberService.getShoppingMemInfo(memAccount));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

}
