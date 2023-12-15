package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;

@Service
public class CustomerService {

	private CustomerDao customerDao;
	
	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void insertReceipt(int loginedMemberId, String title, String body) {
		customerDao.insertReceipt(loginedMemberId, title, body);
	}

	

}