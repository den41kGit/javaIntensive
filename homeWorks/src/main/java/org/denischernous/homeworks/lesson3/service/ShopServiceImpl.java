package org.denischernous.homeworks.lesson3.service;


import org.denischernous.homeworks.lesson3.dao.ShopDao;
import org.denischernous.homeworks.lesson3.dao.ShopDaoImpl;
import org.denischernous.homeworks.lesson3.model.Shop;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    private final ShopDao shopDao = new ShopDaoImpl();

    @Override
    public Shop save(Shop shop) {
        return shopDao.save(shop);
    }

    @Override
    public List<Shop> getAll() {
        return shopDao.getAll();
    }

    @Override
    public Shop update(Shop shop) {
        return shopDao.update(shop);
    }

    @Override
    public int deleteById(int id) {
        return shopDao.deleteById(id);
    }
}
