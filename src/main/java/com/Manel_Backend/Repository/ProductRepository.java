package com.Manel_Backend.Repository;

import com.Manel_Backend.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
