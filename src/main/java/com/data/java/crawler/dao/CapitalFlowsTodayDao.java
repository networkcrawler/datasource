package com.data.java.crawler.dao;

import java.util.List;

import com.data.java.crawler.dto.CapitalFlowsTodayDTO;

public interface CapitalFlowsTodayDao {
	void insertMany(List<CapitalFlowsTodayDTO> list);
}
