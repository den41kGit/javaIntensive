package org.denischernous.homeworks.lesson3.service;

import org.denischernous.homeworks.lesson3.model.Shop;

import java.util.List;

/**
 * Сервис по работе с магазинами
 */
public interface ShopService {
    /**
     * Сохранение книг
     *
     * @param shop
     * @return
     */
    Shop save(Shop shop);

    /**
     * Получение списка книг
     *
     * @return
     */
    List<Shop> getAll();

    /**
     * Обновление данных о книге
     *
     * @param shop
     * @return
     */
    Shop update(Shop shop);

    /**
     * Удаление книги по id
     *
     * @param id
     * @return
     */
    int deleteById(int id);
}
