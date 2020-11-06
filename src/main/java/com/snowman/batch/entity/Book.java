package com.snowman.batch.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2020/11/6 14:27
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @Column(name = "ID")

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PAPERBACK")
    private Integer paperback;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "ISBN_10")
    private String isbn10;

    @Column(name = "DESCRIPTION")
    private String description;
}
