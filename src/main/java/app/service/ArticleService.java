package app.service;

import app.domain.Article;
import app.repository.ArticleRepository;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public void updateArticle(Article userData, String owner) {
        articleRepository.update(userData.getText(), owner);
    }

    public boolean isNameVacant(String name) {
        return !(articleRepository.findByName(name) != null);
    }

    public boolean checkOwner(String name, long id) {
        return articleRepository.findById(id).get().getAuthor().getUsername().equals(name);
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
