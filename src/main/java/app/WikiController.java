package app;

import app.domain.Article;
import app.service.ArticleService;
import app.service.HistoryService;
import app.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
public class WikiController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserDTOService userDTOService;
    @Autowired
    private HistoryService historyService;

    @PostMapping(value = "/createArticle", produces = "application/json;")
    public void createArticle(@RequestBody Article request, Principal principal, HttpServletResponse response) throws IOException {
        if (articleService.isNameVacant(request.getName())) {
            articleService.createArticle(request, principal.getName());
            userDTOService.incrementArtCount(principal.getName());
            historyService.createRecord(request, principal.getName());
        } else {
            response.sendError(418,"Статья уже существует.");
        }
        System.out.println("Создание статьи");
    }


}
