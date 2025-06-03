package com.Manel_Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraRestController {
    @Autowired
    private CompraService compraService;

    @PostMapping
    public String agregarCompra(@RequestBody Compra c) {
        compraService.crearCompra(c);
        return "Compra creada con éxito!";
    }
}
