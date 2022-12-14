package com.cj.cga101g1.member.dao;

import com.cj.cga101g1.member.util.Mem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberRepository extends CrudRepository<Mem,Integer> {
    @Query(value ="select * from mem where MemEmail = ?1",nativeQuery = true)
    Mem findByMemEmail(String  memEmail);

    @Query(value ="select * from mem where memAccount = ?1",nativeQuery = true)
    Mem findByAccount(String  account);

    @Query(value ="select * from mem where memAccount = ?1 and memPassword = ?2",nativeQuery = true)
    Mem findByAccount(String  account,String memPassword);

//    @Query(value ="select  memNo, memAccount, memPassword, memStatus,memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd," +
//            " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus, isMute " +
//            " from mem WHERE memNo = ?1",nativeQuery = true)
    @Query(value ="select * from mem WHERE memNo = ?1",nativeQuery = true)
    Mem getMemSelfInfo(Integer memNo);

    @Query(value ="select MyPic from mem where MemNo = ?1",nativeQuery = true)
    byte[] findMemPicByMemNo(Integer memNo);
}
