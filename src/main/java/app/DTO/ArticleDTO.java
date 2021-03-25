package app.DTO;

import antlr.collections.List;
import app.domain.Article;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ArticleDTO {
    private long articleId;
    private String name;
    private String text;
    private String author;

    public ArticleDTO(long articleId, String name, String text, String author) {
        this.articleId = articleId;
        this.name = name;
        this.text = text;
        this.author = author;
    }

    public ArticleDTO(){}

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static ArticleDTO articleToDTO(Article article) {
        return new ArticleDTO(article.getArticleId(),
                article.getName(),
                article.getText(),
                article.getAuthor().getUsername());
    }

    public static ArrayList<ArticleDTO> articleDTOListToGet(ArrayList<Article> articleList){
        ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
        for (Article article :
                articleList) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticleId(article.getArticleId());
            articleDTO.setName(article.getName());
            list.add(articleDTO);
        }
        return list;
    }
}
