package ru.otus.springaopdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    // Конструкторы, геттеры и сеттеры
    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Геттеры и сеттеры...
}