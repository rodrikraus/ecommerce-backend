package com.Manel_Backend.Controller;

import com.Manel_Backend.Dtos.PurchaseRequest;
import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequest request) {
        try {
            Compra compra = purchaseService.createOrder(request);
            return ResponseEntity.ok(compra);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }
}