package com.cj.cga101g1.member.dao;

import com.cj.cga101g1.member.util.Mem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberRepository extends CrudRepository<Mem,Integer> {
    @Query(value ="select * from mem where MemEmail = ?1",nativeQuery = true)
    Mem findByMemEmail(String  memEmail);
}
