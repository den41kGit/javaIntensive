package org.denischernous.homeworks.lesson3.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.denischernous.homeworks.lesson3.model.Book;
import org.denischernous.homeworks.lesson3.service.BookService;
import org.denischernous.homeworks.lesson3.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Класс сервлет для работы с запросами к БД книг
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final BookService bookService;
    ObjectMapper objectMapper = new ObjectMapper();

    public BookServlet() {
        super();
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<Book> books = bookService.getAll();
        resp.getWriter().write(objectMapper.writeValueAsString(books));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Book book = objectMapper.readValue(req.getInputStream(), Book.class);

            if (book == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book data");
                return;
            }

            Book createdBook = bookService.save(book);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), createdBook);

        } catch (JsonProcessingException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Book data");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Book book = objectMapper.readValue(req.getReader(), Book.class);
            if (book == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book data");
                return;
            }

            Book updatedBook = bookService.update(book);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), updatedBook);
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Book data");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book id");
            return;
        }
        int bookId;
        try {
            bookId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book id");
            return;
        }
        bookService.deleteById(bookId);
    }
}
