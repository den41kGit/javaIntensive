package org.denischernous.homeworks.lesson3.dao;


import org.denischernous.homeworks.lesson3.model.Book;

import java.util.List;

/**
 * Интерфейс для работы с БД
 */
public interface BookDao {

    /**
     * Метод добавления в БД
     * @param book
     * @return
     */
    Book save(Book book);

    /**
     * Метод получения всех данных из БД
     * @return
     */
    List<Book> getAll();

    /**
     * Метод обновления записи в БД по ID
     * @param book
     * @return
     */
    Book update(Book book);

    /**
     * Метод удаления данных из БД по ID
     * @param id
     * @return
     */
    int deleteById(int id);
}
