package org.denischernous.homeworks.lesson3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * POJO класс Магазина
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private Integer id;
    private String city;
    private Integer phone;

}
