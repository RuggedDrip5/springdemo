package ru.otus.springaopdemo.repository;

import ru.otus.springaopdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}