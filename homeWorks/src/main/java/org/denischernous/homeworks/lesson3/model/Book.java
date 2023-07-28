package org.denischernous.homeworks.lesson3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * POJO класс Книги
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private Integer authorId;
    private String title;
    private Integer price;
}
