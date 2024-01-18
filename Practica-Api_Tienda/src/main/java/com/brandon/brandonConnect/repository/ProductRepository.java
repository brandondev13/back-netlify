package com.brandon.brandonConnect.repository;


import com.brandon.brandonConnect.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

