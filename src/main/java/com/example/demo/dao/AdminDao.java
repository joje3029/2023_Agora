package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.CustomerCenter;

@Mapper
public interface AdminDao {
	
	@Select("""
			SELECT C.*, U.uwerId
				FROM `CSTMR_CNSLT_CNTER` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.userUniqId = U.id
			""")
	CustomerCenter getCustomerList();

}