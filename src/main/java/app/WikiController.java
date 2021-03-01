package app;

import app.domain.Article;
import app.service.ArticleService;
import app.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class WikiController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserDTOService userDTOService;

    @PostMapping(value = "/createArticle", produces = "application/json;")
    public void createArticle(@RequestBody Article request, Principal principal){
        articleService.createArticle(request,principal.getName());
        userDTOService.incrementArtCount(principal.getName());
        System.out.println("Создание статьи");
    }
}
