package org.denischernous.homeworks.lesson3.service;

import org.denischernous.homeworks.lesson3.model.Author;

import java.util.List;

/**
 * Сервис по работе с авторами
 */
public interface AuthorService {
    /**
     * Сохранение книг
     *
     * @param author
     * @return
     */
    Author save(Author author);

    /**
     * Получение списка книг
     *
     * @return
     */
    List<Author> getAll();

    /**
     * Обновление данных о книге
     *
     * @param author
     * @return
     */
    Author update(Author author);

    /**
     * Удаление книги по id
     *
     * @param id
     * @return
     */
    int deleteById(int id);
}
