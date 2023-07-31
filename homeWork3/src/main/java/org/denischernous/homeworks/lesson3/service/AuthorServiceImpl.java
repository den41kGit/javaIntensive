package org.denischernous.homeworks.lesson3.service;

import org.denischernous.homeworks.lesson3.dao.AuthorDao;
import org.denischernous.homeworks.lesson3.dao.AuthorDaoImpl;
import org.denischernous.homeworks.lesson3.model.Author;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public int deleteById(int id) {
        return authorDao.deleteById(id);
    }
}
