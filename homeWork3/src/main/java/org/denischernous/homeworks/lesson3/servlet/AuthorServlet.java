package org.denischernous.homeworks.lesson3.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.denischernous.homeworks.lesson3.model.Author;
import org.denischernous.homeworks.lesson3.service.AuthorService;
import org.denischernous.homeworks.lesson3.service.AuthorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Класс сервлет для работы с запросами к БД авторов
 */
@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService;
    ObjectMapper objectMapper = new ObjectMapper();

    public AuthorServlet() {
        super();
        authorService = new AuthorServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<Author> authors = authorService.getAll();
        resp.getWriter().write(objectMapper.writeValueAsString(authors));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Author author = objectMapper.readValue(req.getInputStream(), Author.class);

            if (author == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Author data");
                return;
            }

            Author createdAuthor = authorService.save(author);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), createdAuthor);

        } catch (JsonProcessingException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Author data");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Author author = objectMapper.readValue(req.getReader(), Author.class);
            if (author == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Author data");
                return;
            }

            Author updatedAuthor = authorService.update(author);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), updatedAuthor);
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Author data");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("author_id");
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Author id");
            return;
        }
        int authorId;
        try {
            authorId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Author id");
            return;
        }
        authorService.deleteById(authorId);
    }
}
