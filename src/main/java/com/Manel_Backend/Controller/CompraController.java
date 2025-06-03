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
}