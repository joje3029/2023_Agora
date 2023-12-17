package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.CustomerCenter;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class AdminService {

	private AdminDao adminDao;

	public AdminService(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public List<CustomerCenter> getCustomerCenterList(int itemsInAPage, int limitStart) {
		return adminDao.getCustomerCenterList(itemsInAPage, limitStart);
	}

	public int getCustomerlistCnt() {
		return adminDao.getCustomerlistCnt();
	}

	public CustomerCenter getdetailCustomer(int id) {
		return adminDao.getdetailCustomer(id);
	}
	
	

}