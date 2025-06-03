package com.Manel_Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Models.CompraItem;
import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Repository.CompraRepository;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductService productService;

    public List<Compra> verCompras() {
        return compraRepository.findAll();
    }

    public Compra obtenerPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    public void crearCompra(Compra c) {
        //Reduzco el stock de los productos comprados
        //Aca puedo pedirle al frontend que mande todos los datos del producto o hacerlo yo mismo, opto por la ultima
        for (CompraItem ci : c.getItems()) {
            Long productId = ci.getProduct().getId();
            Product product = productService.buscarProducto(productId);
            ci.setProduct(product);
            ci.getProduct().setStock(ci.getProduct().getStock() - ci.getQuantity());
        }
        compraRepository.save(c);
    }

}
