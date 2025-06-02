package com.Manel_Backend.Controller;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public String verCompras(Model model) {
        List<Compra> listaCompras = compraService.verCompras();
        model.addAttribute("compras", listaCompras);
        return "listadoCompras.html";
    }

    /*
    @GetMapping("/agregarProducto")
    public String agregarProductoForm(Model model) {
        model.addAttribute("nuevoProducto", new Product());
        return "agregarProducto.html";
    }

    @PostMapping("/guardarProductoNuevo")
    public String agregarProducto(@ModelAttribute Product nuevoProducto) {
        compraService.crearProducto(nuevoProducto);
        return "redirect:/productos";
    }


    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable long id) {
        compraService.borrarProducto(id);
        return "redirect:/productos";
    }
    */



}