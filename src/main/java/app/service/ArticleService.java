package app.service;

import app.domain.Article;
import app.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(){

    }

    public boolean isNameVacant(String name){
        return articleRepository.findByName(name) != null;
    }
}
