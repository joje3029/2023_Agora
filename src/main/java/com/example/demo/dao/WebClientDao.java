package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebClientDao {
	
	@Insert({
        "<script>",
        "INSERT INTO BookList (title, link, author, pubDate, cover, categoryName) VALUES ",
        "<foreach collection='itemList' item='item' separator=','>",
            "(",
            "#{item.title},",
            "#{item.link},",
            "#{item.author},",
            "#{item.pubDate},",
            "#{item.cover},",
            "#{item.categoryName}",
            ")",
        "</foreach>",
        "</script>"
    })
    void insertBook(List<Map<String, Object>> itemList);

	
}