package org.denischernous.homeworks.lesson3.dao;


import org.denischernous.homeworks.lesson3.model.Shop;

import java.util.List;

/**
 * Интерфейс для работы с БД
 */
public interface ShopDao {

    /**
     * Метод добавления в БД
     * @param shop
     * @return
     */
    Shop save(Shop shop);

    /**
     * Метод получения всех данных из БД
     * @return
     */
    List<Shop> getAll();

    /**
     * Метод обновления записи в БД по ID
     * @param shop
     * @return
     */
    Shop update(Shop shop);

    /**
     * Метод удаления данных из БД по ID
     * @param id
     * @return
     */
    int deleteById(int id);
}
