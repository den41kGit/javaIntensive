package org.denischernous.homeworks.lesson3.service;

import org.denischernous.homeworks.lesson3.dao.BookDao;
import org.denischernous.homeworks.lesson3.dao.BookDaoImpl;
import org.denischernous.homeworks.lesson3.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public int deleteById(int id) {
        return bookDao.deleteById(id);
    }
}
