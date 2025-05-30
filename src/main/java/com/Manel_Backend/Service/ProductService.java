package com.Manel_Backend.Service;

import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository prodRepo;

    public List<Product> verProductos() {
        return prodRepo.findAll();
    }

    @Transactional
    public void crearProducto(Product p) {
        prodRepo.save(p);
    }

    @Transactional
    public void borrarProducto(Long id) {
        prodRepo.deleteById(id);
    }

    public Product buscarProducto(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Transactional
    public void actualizarProducto(Product p) {
        prodRepo.save(p);
    }
}
