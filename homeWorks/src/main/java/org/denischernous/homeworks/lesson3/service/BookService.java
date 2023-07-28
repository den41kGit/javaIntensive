package org.denischernous.homeworks.lesson3.service;

import org.denischernous.homeworks.lesson3.model.Book;

import java.util.List;

/**
 * Сервис по работе с книгами
 */
public interface BookService {
    /**
     * Сохранение книг
     *
     * @param book
     * @return
     */
    Book save(Book book);

    /**
     * Получение списка книг
     *
     * @return
     */
    List<Book> getAll();

    /**
     * Обновление данных о книге
     *
     * @param book
     * @return
     */
    Book update(Book book);

    /**
     * Удаление книги по id
     *
     * @param id
     * @return
     */
    int deleteById(int id);
}
