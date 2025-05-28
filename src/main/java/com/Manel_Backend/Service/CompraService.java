package com.Manel_Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Repository.CompraRepository;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> verCompras() {
        return compraRepository.findAll();
    }

    public Compra obtenerPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

}
