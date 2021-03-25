package app.service;

import app.DTO.ArticleDTO;
import app.domain.Article;
import app.repository.ArticleRepository;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle(Article userData, String owner) {
        Article article = new Article();
        article.setAuthor(userRepository.findByUsername(owner));
        article.setName(userData.getName());
        article.setText(userData.getText());
        article.setDeleted(false);
        articleRepository.save(article);
        return article;
    }

    public void updateArticle(Article userData, String name) {
        System.out.println("Text = " + userData.getText());
        articleRepository.update(userData.getText(), name);
    }

    public boolean isNameVacant(String name) {
        return !(articleRepository.findByName(name) != null);
    }

    public boolean checkOwner(String name, long id) {
        return articleRepository.findById(id).get().getAuthor().getUsername().equals(name);
    }

    public List<ArticleDTO> getAllArticleId(){
        ArrayList<Article> articles = articleRepository.findAllIdAndName();
        return ArticleDTO.articleDTOListToGet(articles);
    }

    public void deleteArticle(long id) {
        articleRepository.delete(id);
    }

    public Article getArticleById(long id) {
        if (articleRepository.findById(id).isPresent())
            return articleRepository.findById(id).get();
        else return null;
    }


}
