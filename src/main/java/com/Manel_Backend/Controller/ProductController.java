package com.Manel_Backend.Controller;

import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String verProductos(Model model) {
        List<Product> listaProductos = productService.verProductos();
        model.addAttribute("productos", listaProductos);
        return "listadoProductos";
    }

    @GetMapping("/agregarProducto")
    public String agregarProductoForm(Model model) {
        model.addAttribute("nuevoProducto", new Product());
        return "agregarProducto";
    }

    @PostMapping("/guardarProductoNuevo")
    public String agregarProducto(@ModelAttribute Product nuevoProducto) {
        productService.crearProducto(nuevoProducto);
        return "redirect:/productos";
    }

    @GetMapping("/modificarProducto/{id}")
    public String modificarProducto(@PathVariable long id, Model model) {
        Product producto = productService.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "modificarProducto";
    }

    @PostMapping("guardarModificacionProducto")
    public String guardarModificacionProducto(@ModelAttribute Product producto) {
        productService.crearProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable long id) {
        productService.borrarProducto(id);
        return "redirect:/productos";
    }



}