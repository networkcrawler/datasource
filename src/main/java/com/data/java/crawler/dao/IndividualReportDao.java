package com.data.java.crawler.dao;

import java.util.List;

import com.data.java.crawler.dto.EastMoneyIndividualReportDTO;

public interface IndividualReportDao {
	void insertMany(List<EastMoneyIndividualReportDTO> list);
	void updateMany(List<EastMoneyIndividualReportDTO> list);
	EastMoneyIndividualReportDTO findByTitle(String title);
}
