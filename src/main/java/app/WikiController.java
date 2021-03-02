package app;

import app.DTO.ArticleDTO;
import app.domain.Article;
import app.service.ArticleService;
import app.service.HistoryService;
import app.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "/updateArticle", produces = "application/json;")
    public void updateArticle(@RequestBody Article request, Principal principal, HttpServletResponse response) throws IOException {
        if (!articleService.isNameVacant(request.getName())) {
            if (userDTOService.checkEditAbility(request, principal.getName())) {
                articleService.updateArticle(request, principal.getName());
                historyService.createRecord(request, principal.getName());
            } else {
                response.sendError(418, "Ошибка прав редактирование");
            }
        } else {
            response.sendError(418, "Такой статьи не существует.");
        }
    }

    @PutMapping(value = "/createArticle", produces = "application/json;")
    public void createArticle(@RequestBody Article request, Principal principal, HttpServletResponse response) throws IOException {
        if (articleService.isNameVacant(request.getName())) {
            articleService.createArticle(request, principal.getName());
            userDTOService.incrementArtCount(principal.getName());
            historyService.createRecord(request, principal.getName());
        } else {
            response.sendError(418, "Статья уже существует.");
        }
        System.out.println("Создание статьи");
    }

    @DeleteMapping(value = "/deleteArticle", produces = "application/json")
    public void deleteArticle(@RequestParam long id, HttpServletResponse response, Principal principal) throws IOException {
        if (articleService.checkOwner(principal.getName(), id)) {
            articleService.deleteArticle(id);
            historyService.createRecord(articleService.getArticleById(id), principal.getName());
        } else {
            response.sendError(418, "Вы не являетесь владельцем статьи.");
        }
    }

    @GetMapping("/getArticle")
    public ArticleDTO getArticle(@RequestParam long id, HttpServletResponse response) throws IOException {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            if (article.isDeleted()) {
                response.sendError(418, "Несуществующая статья");
                return null;
            } else {
                return ArticleDTO.articleToDTO(article);
            }
        } else {
            response.sendError(418, "Несуществующая статья");
            return null;
        }
    }



}
