package org.denischernous.homeworks.lesson3.dao;


import org.denischernous.homeworks.lesson3.model.Author;

import java.util.List;

/**
 * Интерфейс для работы с БД
 */
public interface AuthorDao {

    /**
     * Метод добавления в БД
     *
     * @param author
     * @return
     */
    Author save(Author author);

    /**
     * Метод получения всех данных из БД
     *
     * @return
     */
    List<Author> getAll();

    /**
     * Метод обновления записи в БД по ID
     *
     * @param author
     * @return
     */
    Author update(Author author);

    /**
     * Метод удаления данных из БД по ID
     *
     * @param id
     * @return
     */
    int deleteById(int id);
}
