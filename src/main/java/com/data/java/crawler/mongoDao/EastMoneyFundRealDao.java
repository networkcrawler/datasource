package com.data.java.crawler.mongoDao;

import java.util.List;

import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;

public interface EastMoneyFundRealDao {
	void insert(List<EastMoneyFundRealPerDTO> lists);
	EastMoneyFundRealPerDTO findOneBySymbol(String symbol);
	void update(List<EastMoneyFundRealPerDTO> lists);
}
