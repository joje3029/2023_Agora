package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
	
	@Select("""
			
			""")
	int getUsersCnt(String searchKeywordType, String searchKeyword);

}