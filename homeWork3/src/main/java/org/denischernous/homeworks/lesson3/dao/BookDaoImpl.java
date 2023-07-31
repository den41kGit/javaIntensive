package org.denischernous.homeworks.lesson3.dao;

import org.denischernous.homeworks.lesson3.config.DbConnector;
import org.denischernous.homeworks.lesson3.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с Книгами
 */
public class BookDaoImpl implements BookDao {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book save(Book book) {

        String sql = "INSERT INTO books (author_id, book_title, book_price) VALUES(?, ?, ?) ";
        int bookId = 0;

        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, book.getAuthorId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bookId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        book.setId(bookId);
        return book;
    }

    public List<Book> getAll() {
        List<Book> booksList = new ArrayList<>();

        String sql = "SELECT books.*, authors.author_name, authors.author_surname FROM books " +
                "INNER JOIN authors ON authors.author_id = books.author_id ";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("book_id");
                int author_id = rs.getInt("author_id");
                String title = rs.getString("book_title");
                int price = rs.getInt("book_price");
                Book book = new Book(id, author_id, title, price);
                booksList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksList;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE books SET author_id = ?, book_title = ?, book_price = ? " +
                "WHERE book_id = ? ";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, book.getAuthorId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getPrice());
            preparedStatement.setInt(4, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

        @Override
    public int deleteById(int id) {
        if (id <= 0) {
            throw new RuntimeException("Other type of exception");
        }
        String sql = "DELETE FROM books " +
                "WHERE book_id = ? ";

        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
