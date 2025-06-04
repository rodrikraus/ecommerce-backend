package com.Manel_Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Models.CompraItem;
import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Repository.CompraRepository;

import jakarta.transaction.Transactional;

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
            ci.setCompra(c);
        }
        compraRepository.save(c);
    }

    @Transactional
    public void borrarCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra buscarCompra(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Transactional
    public void actualizarCompra(Compra c) {
        Compra compraExistente = compraRepository.findById(c.getId()).orElse(null);
        //Debo modificar el stock en base a las modificaciones de la compra c
        //Miro item por item, si aumento el pedido debo reducir ese stock, si disminuyo el pedido debo aumentar el stock
        for(CompraItem i : c.getItems()) {
            for(CompraItem j : compraExistente.getItems()) {
                if(i.getId().equals(j.getId())) {
                    if(i.getQuantity()>j.getQuantity()) {
                        int difference = i.getQuantity()-j.getQuantity();
                        i.getProduct().setStock(i.getProduct().getStock() - difference);
                    } else if(i.getQuantity()<j.getQuantity()) {
                        int difference = j.getQuantity()-i.getQuantity();
                        i.getProduct().setStock(i.getProduct().getStock() + difference);
                    }
                }
            }
        }
        compraRepository.save(c);
    }

}
