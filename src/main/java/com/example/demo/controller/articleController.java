package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class articleController {
	@Autowired
	private ArticleRepository articleRepository;
	@GetMapping("/articles/new")
	public String newArticleForm(){
		return "articles/new";
	}
	@PostMapping("/articles/create")
	public String createArticle(ArticleForm form) {
		//DTO=>Entity
		Article article=form.toEntity();
		//Repository가 Entity를 db에 저장
		Article saved=articleRepository.save(article);
		log.info(saved.toString());
		return "redirect:/articles/"+saved.getId();
	}
	@GetMapping("/articles/{id}")
	public String show(@PathVariable Long id,Model model) {
		log.info(id.toString());
		//1.id로 데이터 가져옴
		Article articleEntity=articleRepository.findById(id).orElse(null);
		//2.가져온 데이터를 모델에 등록
		model.addAttribute("article",articleEntity);
		//3.보여줄 페이지 설정
		return "articles/show";
	}
	@GetMapping("/articles/list")
	public String list(Model model) {
		//모든 article 가져오기
		List<Article> list=articleRepository.findAll();
		model.addAttribute("list",list);
		return "articles/list";
	}
	@GetMapping("/articles/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
		Article article=articleRepository.findById(id).orElse(null);
		model.addAttribute("article",article);
		return "articles/edit";
	}
	@PostMapping("/articles/update")
	public String update(ArticleForm form) {
		log.info(form.getTitle());
		
		//DTO를 엔티티로 변환
		Article articleEntity=form.toEntity();
		//엔티티를 DB에 저장
		Article target=articleRepository.findById(articleEntity.getId()).orElse(null);
		if(target!=null) {
			articleRepository.save(articleEntity);
		}
		//수정결과 페이지로 리다이렉트한다.
	return "redirect:/articles/"+articleEntity.getId();
	}
	@RequestMapping("/articles/delete/{id}")
	public String delete(@PathVariable Long id,RedirectAttributes rttr) {
		Article article=articleRepository.findById(id).orElse(null);
		if(article!=null) {
			articleRepository.delete(article);
			rttr.addFlashAttribute("msg","삭제완료");
		}
		return "redirect:/articles/list";
	}
}
