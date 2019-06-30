package com.data.java.crawler.dao;

import com.data.java.crawler.dto.StockPoolDTO;

import java.util.List;

public interface StockPoolDao {
    void insertMany(List<StockPoolDTO> list);
    void insertOne(StockPoolDTO stockPoolDTO);
    StockPoolDTO selectOne(String code);
}
