package com.Manel_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Service.ProductService;

@RestController
@RequestMapping("/api/productos")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public String agregarProducto(@RequestBody Product p) {
        productService.crearProducto(p);
        return "Producto creado con éxito!";
    }

    @GetMapping
    @ResponseBody
    public List<Product> verProductos() {
        return productService.verProductos();
    }

    @DeleteMapping("/borrar_producto/{id}")
    public void borrarProducto(@PathVariable Long id) {
        productService.borrarProducto(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product buscarProducto(@PathVariable Long id) {
        return productService.buscarProducto(id);
    }

    /* PLACEHOLDER, NO VEO LA NECESIDAD DE ACTUALIZAR UN PRODUCTO DESDE EL FRONT PUDIENDOSE HACER DESDE EL BACK
    @PutMapping("/actualizar_producto/{id}")
    @ResponseBody
    public void actualizarProduct(@PathVariable("id") long id, @RequestBody Product p) {
        Product producto = productService.buscarProducto(id);
        if(!producto.equals(null)) {
            producto.setNombre(p.getNombre());
            producto.setApellido(p.getApellido());
            producto.setEmail(p.getEmail());
            productService.actualizarProduct(p);
        }
    }
    */
}
