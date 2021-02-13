package app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long articleId;

    private String name;
    private Date date_change;
    private String text;

    @ManyToOne
    private User author;

    public Article(long articleId, String name, Date date_change, String text, User author) {
        this.articleId = articleId;
        this.name = name;
        this.date_change = date_change;
        this.text = text;
        this.author = author;
    }

    public Article() {
    }

}
