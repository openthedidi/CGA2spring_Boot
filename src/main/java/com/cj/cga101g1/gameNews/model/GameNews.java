package com.cj.cga101g1.gameNews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "gamenews")
@Component
public class GameNews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameNewsNo;
    private Integer gamePlatformNo;
    private Integer managerNo;
    private String gameNewsTitle;
    private String gameNewsContent;
    private byte[] gameNewsPic;

    @Transient
    private String picUrl;
}
