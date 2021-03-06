package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>  {

	List<Article> findByTitleAndCategory(String title, String category);

}
