package com.data.java.crawler.dao;

import com.data.java.crawler.dto.MainInflowRankingDTO;

import java.util.List;

public interface MainInflowRankingDao {
    void insertMany(List<MainInflowRankingDTO> lists);
    List<MainInflowRankingDTO> findBySymbolAndCreate(String symbol);
    void updateBySymbolAndCreate(List<MainInflowRankingDTO> lists);
}
