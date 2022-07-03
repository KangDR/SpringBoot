package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity//DB가 이 클래스로 테이블을 생성한다.
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//db가 아이디를 자동생성
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	public void patch(Article article) {
		if(article.title!=null) {
			this.title=article.title;
		}
		if(article.content!=null) {
			this.content=article.content;
		}
	}
}
