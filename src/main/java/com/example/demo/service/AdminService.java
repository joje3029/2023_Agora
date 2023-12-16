package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.vo.CustomerCenter;

@Service
public class AdminService {
	
	private AdminDao adminDao;
	
	public AdminService(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public CustomerCenter getCustomerList() {
		return adminDao.getCustomerList();
	}
	
	
}