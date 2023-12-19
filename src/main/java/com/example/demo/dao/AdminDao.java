package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.CustomerCenter;
import com.example.demo.vo.WithdrowReason;

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
	
	@Select("""
			SELECT 
			    withdrawalMonth,
			    reasons.reason,
			    COALESCE(COUNT(WITHDROW_REASON.id), 0) AS COUNT
			FROM (
			    SELECT DISTINCT DATE_FORMAT(withDrowDate, '%Y-%m') AS withdrawalMonth
			    FROM WITHDROW_REASON
			    WHERE withDrowDate BETWEEN '2023-01-01' AND '2023-12-31'
			) AS months
			CROSS JOIN (
			    SELECT 'extReaseon' AS reason
			    UNION
			    SELECT 'notUse' AS reason
			    UNION
			    SELECT 'otherSite' AS reason
			    UNION
			    SELECT 'contentsDiscontent' AS reason
			) AS reasons
			LEFT JOIN WITHDROW_REASON
			ON reasons.reason = WITHDROW_REASON.reason AND months.withdrawalMonth = DATE_FORMAT(WITHDROW_REASON.withDrowDate, '%Y-%m')
			GROUP BY withdrawalMonth, reasons.reason
			ORDER BY withdrawalMonth, reasons.reason;
			""")
	public List<WithdrowReason> getWithdrowReason();
	
	
}