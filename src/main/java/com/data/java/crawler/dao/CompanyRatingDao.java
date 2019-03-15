package com.data.java.crawler.dao;

import java.util.List;

import com.data.java.crawler.dto.CompanyRatingDTO;

public interface CompanyRatingDao {
	CompanyRatingDTO findByHref(String href);
	void insertMany(List<CompanyRatingDTO> list);
}
