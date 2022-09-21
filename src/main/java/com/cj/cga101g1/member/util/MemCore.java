package com.cj.cga101g1.member.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MemCore {
    private boolean successful;
    private String message;
    private String initlocation;
}
