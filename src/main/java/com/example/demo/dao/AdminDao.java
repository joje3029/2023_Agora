package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.CustomerCenter;

@Mapper
public interface AdminDao {
	
	@Select("""
			SELECT C.*, U.uwerId
				FROM `CSTMR_CNSLT_CNTER` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.userUniqId = U.id
				ORDER BY C.id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			""")
	public List<CustomerCenter> getCustomerCenterList(int itemsInAPage, int limitStart);
	
	@Select("""
			SELECT COUNT(*)
				FROM CSTMR_CNSLT_CNTER
			""")
	public int getCustomerlistCnt();
	
	@Select("""
			SELECT C.*, U.uwerId, U.email
				FROM `CSTMR_CNSLT_CNTER` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.userUniqId = U.id
				WHERE C.id = #{id}
				ORDER BY C.id DESC
			""")
	public CustomerCenter getdetailCustomer(int id);
	
	@Update("""
			UPDATE CSTMR_CNSLT_CNTER 
				SET ricfldSndngYn = 4
				WHERE id = #{id}
			""")
	public Object modifyRicFail(int id);
	
	@Update("""
			UPDATE CSTMR_CNSLT_CNTER 
				SET ricfldSndngYn = 1
				WHERE id = #{id}
			""")
	public void modifyRicSucess(int id);
	
	
}