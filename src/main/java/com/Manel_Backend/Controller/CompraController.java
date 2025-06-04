package com.Manel_Backend.Controller;

import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public String guardarModificacionCompra(@ModelAttribute Compra compraModificada) {
        // 1. Fetch the existing Compra
        Compra compraExistente = compraService.buscarCompra(compraModificada.getId());

        if (compraExistente != null) {
            // 2. Update basic fields
            compraExistente.setEmail(compraModificada.getEmail());
            compraExistente.setShippingAddress(compraModificada.getShippingAddress());
            // totalAmount will be recalculated

            // 3. Process Items - Update quantities for existing items
            if (compraModificada.getItems() != null && compraExistente.getItems() != null) {
                for (com.Manel_Backend.Models.CompraItem itemModificado : compraModificada.getItems()) {
                    for (com.Manel_Backend.Models.CompraItem itemExistente : compraExistente.getItems()) {
                        // Ensure both item IDs are not null before comparing
                        if (itemModificado.getId() != null && itemModificado.getId().equals(itemExistente.getId())) {
                            itemExistente.setQuantity(itemModificado.getQuantity());
                            // unitPrice and product should generally not change here unless explicitly allowed
                            break; 
                        }
                    }
                }
            }
            
            // 4. Recalculate Total Amount
            BigDecimal nuevoTotal = BigDecimal.ZERO;
            if (compraExistente.getItems() != null) {
                for (com.Manel_Backend.Models.CompraItem item : compraExistente.getItems()) {
                    // Ensure unitPrice and quantity are not null to avoid NullPointerException
                    if (item.getUnitPrice() != null && item.getQuantity() > 0) {
                        nuevoTotal = nuevoTotal.add(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
                    }
                }
            }
            compraExistente.setTotalAmount(nuevoTotal);

            // 5. Save Updated Compra
            compraService.crearCompra(compraExistente); // Assuming crearCompra can handle updates
        }
        return "redirect:/compras";
    }

    @GetMapping("/borrar/{id}")
    public String borrarCompra(@PathVariable long id) {
        compraService.borrarCompra(id);
        return "redirect:/compras";
    }
}