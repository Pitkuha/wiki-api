package app.domain;

import app.DTO.UserDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WEBUSER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String username;
    private String password;
    private int article_count;

    @OneToMany
    private List<Article> userArticle;


    public User(String username, String password, int article_count, List<Article> list) {
        this.username = username;
        this.password = password;
        this.article_count = article_count;
        this.userArticle = list;
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getArticle_count() {
        return article_count;
    }

    public void setArticle_count(int article_count) {
        this.article_count = article_count;
    }

    public List<Article> getUserArticle() {
        return userArticle;
    }

    public void setUserArticle(List<Article> userArticle) {
        this.userArticle = userArticle;
    }

}
