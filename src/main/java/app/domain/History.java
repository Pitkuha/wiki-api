package app.domain;

import javax.persistence.*;

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

}
