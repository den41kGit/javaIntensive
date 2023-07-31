package org.denischernous.homeworks.lesson3.dao;

import org.denischernous.homeworks.lesson3.config.DbConnector;
import org.denischernous.homeworks.lesson3.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao{
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author save(Author author) {
        String sql = "INSERT INTO authors (author_name, author_surname) VALUES(?, ?) ";
        int authorId = 0;

        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                authorId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        author.setId(authorId);
        return author;
    }

    @Override
    public List<Author> getAll() {
        List<Author> authorsList = new ArrayList<>();

        String sql = "SELECT authors.*, books.book_title FROM authors " +
                "INNER JOIN books ON books.book_id = authors.author_id ";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("author_id");
                String name = rs.getString("author_name");
                String surname = rs.getString("author_surname");
                Author author = new Author(id, name, surname);
                authorsList.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorsList;
    }

    @Override
    public Author update(Author author) {
        String sql = "UPDATE authors SET author_name = ?, author_surname = ? " +
                "WHERE author_id = ? ";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setInt(4, author.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public int deleteById(int id) {
        if (id <= 0) {
            throw new RuntimeException("Other type of exception");
        }
        String sql = "DELETE FROM authors " +
                "WHERE author_id = ? ";

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

