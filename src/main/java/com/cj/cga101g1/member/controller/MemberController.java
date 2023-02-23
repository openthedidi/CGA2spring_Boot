package com.cj.cga101g1.member.controller;


import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.member.util.Mem;
import com.cj.cga101g1.util.jwt.JwtTokenUtils;
import com.cj.cga101g1.util.jwt.JwtUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import java.io.IOException;


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
        String token = jwtToken.createToken(memResult.getUsername(), "000", false); // 取得token
//        token = "token=" + token + "; path=/; secure; HttpOnly";
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


    @PostMapping("/RegisterServlet")
    public ResponseEntity<Mem> register(@RequestBody @Valid Mem mem) {
        Mem memResult = memberService.newMember(mem);
        if (memResult != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(memResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(memResult);
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
        System.out.println("開始取會員資料");
        JwtTokenUtils jwtToken = new JwtTokenUtils();
        if (jwtToken.validateToken(token)) {
            String memAccount = jwtToken.getUsername(token);
            Mem mem = memberService.getMemSelfInfo(memberService.getMemByMemAccount(memAccount));
            mem.setMessage("成功");
            mem.setSuccessful(true);
            System.out.println("驗證成功");
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
        return memberService.showMemSelfPic(memNo);
    }

    ;

    @PutMapping("/MemEditServlet/{memNo}")
    public ResponseEntity<Mem> memEdit(@RequestBody @Valid Mem mem,
                                       @PathVariable Integer memNo) {
        Mem memResult = memberService.memEdit(memNo, mem);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memResult);
    }

    @PostMapping("/memSessionTest")
    public void memSessionTest(HttpSession session) {
        System.out.println(session.getId());
    }

}
