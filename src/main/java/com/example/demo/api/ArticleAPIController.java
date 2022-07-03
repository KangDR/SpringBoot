package com.example.demo.api;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;

@RestController // Rest Api 용 컨트롤러 json반환
public class ArticleAPIController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/api/articles")
	public List<Article> index() {
		return articleService.index();
	}

	@GetMapping("/api/articles/{id}")
	public Article article(@PathVariable Long id) {
		return articleService.show(id);
	}

	@PostMapping("/api/articles")
	public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
		Article created = articleService.create(dto);
		return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PatchMapping("/api/articles/{id}")
	public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id) {
		Article updated = articleService.update(id, dto);
		return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}


	@DeleteMapping("/api/articles/{id}") 
	public ResponseEntity<Article>
	delete(@PathVariable Long id) { 
		Article deleted=articleService.deleted(id);
		return (deleted!=null)?
				ResponseEntity.status(HttpStatus.NO_CONTENT).build():
				ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
			; 
	}
}
