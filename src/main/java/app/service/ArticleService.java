package app.service;

import app.domain.Article;
import app.repository.ArticleRepository;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle(Article userData, String owner){
        Article article = new Article();
        article.setAuthor(userRepository.findByUsername(owner));
        article.setName(userData.getName());
        article.setText(userData.getText());
        articleRepository.save(article);
        return article;
    }

    public Article updateArticle(Article userData, String owner){
        return null;
    }

    public boolean isNameVacant(String name){
        return !(articleRepository.findByName(name) != null);
    }

}
