package com.data.java.crawler.dao;

import java.util.List;

import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;

public interface EastMoneyFundRealDao {
	void insert(List<EastMoneyFundRealPerDTO> lists);
	List<EastMoneyFundRealPerDTO> findOneBySymbol(String symbol);
	void update(List<EastMoneyFundRealPerDTO> lists);
}
