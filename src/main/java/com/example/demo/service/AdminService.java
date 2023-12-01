package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;

@Service
public class AdminService {
	
	private AdminDao adminDao;
	
	public AdminService(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
}