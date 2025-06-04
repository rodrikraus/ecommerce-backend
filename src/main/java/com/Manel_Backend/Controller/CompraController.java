package com.Manel_Backend.Controller;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Models.Product;
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

    @GetMapping("/modificarCompra/{id}")
    public String modificarCompra(@PathVariable long id, Model model) {
        Compra compra = compraService.buscarCompra(id);
        model.addAttribute("compra", compra);
        return "modificarCompra.html";
    }

    @PostMapping("guardarModificacionCompra")
    public String guardarModificacionCompra(@ModelAttribute Compra compra) {
        compraService.crearCompra(compra);
        return "redirect:/compras";
    }

    @GetMapping("/borrar/{id}")
    public String borrarCompra(@PathVariable long id) {
        compraService.borrarCompra(id);
        return "redirect:/compras";
    }
}