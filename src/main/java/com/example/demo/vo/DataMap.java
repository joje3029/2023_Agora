package com.example.demo.vo;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMap {
	private List<String> categories;
	private List<Map<String,Object>> series;
	
}
