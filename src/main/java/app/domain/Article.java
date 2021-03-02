package app.domain;

import javax.persistence.*;

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long articleId;

    private String name;
    private String text;
    private boolean deleted;

    @ManyToOne
    private User author;


    public Article(long articleId, String name, String text, User author, boolean deleted) {
        this.articleId = articleId;
        this.name = name;
        this.text = text;
        this.author = author;
        this.deleted = deleted;
    }

    public Article() {
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
