package com.cj.cga101g1.gamecompany.dao;

import com.cj.cga101g1.gamecompany.util.GameCompany;

public interface GameCompanyDao {
    GameCompany findByNo (Integer gameCompanyNo);
}
