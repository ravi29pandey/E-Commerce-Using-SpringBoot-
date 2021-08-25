package com.sheryians.major.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data /*Data Lombok create getters - setters for entities*/
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /*telling JPA to manage database*/
    @Column(name="category_id")
    private int id;

    private  String name;

}
