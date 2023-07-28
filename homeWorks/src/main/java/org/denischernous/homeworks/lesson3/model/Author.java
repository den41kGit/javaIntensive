package org.denischernous.homeworks.lesson3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO класс Автора
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Integer id;
    private String name;
    private String surname;

}
