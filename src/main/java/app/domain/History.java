package app.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private java.util.Date date_change;

    @ManyToOne
    private Article article;
    @ManyToOne
    private User user;

    public History(Date date_change, Article article, User user) {
        this.date_change = date_change;
        this.article = article;
        this.user = user;
    }

    public History() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_change() {
        return date_change;
    }

    public void setDate_change(Date date_change) {
        this.date_change = date_change;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
