package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;

@Service//서비스 객체를 생성
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	public List<Article> index() {
		return articleRepository.findAll();
	}
	public Article show(Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	public Article create(ArticleForm dto) {
		Article article=dto.toEntity();
		if(article.getId()!=null) {
			return null;
		}
		return articleRepository.save(article);
	}
	public Article update(Long id, ArticleForm dto) {
		Article article=dto.toEntity();
		Article target=articleRepository.findById(id).orElse(null);
		if(target==null||id!=article.getId()) {
			return null;
		}
		target.patch(article);
		Article updated=articleRepository.save(target);
		return updated;
	}
	public Article deleted(Long id) {
		Article	target=articleRepository.findById(id).orElse(null); 
		if(target==null) { 
			return null; 
		}
		articleRepository.delete(target);
		return target;
	}
}
