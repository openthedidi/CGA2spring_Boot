package com.cj.cga101g1.member.controller;


import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.member.util.Mem;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("CGA101G1/mem")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;



    @PostMapping("/RegisterServlet")
    public ResponseEntity<Mem> register(@RequestBody @Valid Mem mem){
       Mem memResult =memberService.newMember(mem);
       if(memResult!=null) {
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(memResult);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(memResult);
       }
    };

    @GetMapping("/getMemByMemEmail")
    public ResponseEntity<Mem> getMemByMemEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemByMemEmail(email));
    }

    @PutMapping("/MemEditServlet/{memNo}")
    public ResponseEntity<Mem> memEdit(@RequestBody @Valid Mem mem,
                                       @PathVariable Integer memNo){
        Mem memResult =memberService.memEdit(memNo,mem);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memResult);
    };

}
