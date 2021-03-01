package app.service;

import app.domain.Article;
import app.domain.History;
import app.repository.ArticleRepository;
import app.repository.HistoryRepository;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;


    public HistoryService(HistoryRepository historyRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public History createRecord(Article article, String userName){
        History history = new History(new Date()
                , articleRepository.findByName(article.getName())
                , userRepository.findByUsername(userName));
        historyRepository.save(history);
        return history;
    }
}
