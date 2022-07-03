package com.example.demo.dto;

import com.example.demo.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ArticleForm {
	private Long id;
	private String title;
	private String content;
	public Article toEntity() {
		Article article=new Article(id,title,content);
		return article;
	}
	public ArticleForm() {
		
	}
}
