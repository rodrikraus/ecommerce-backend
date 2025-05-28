package com.Manel_Backend.Service;

import com.Manel_Backend.Dtos.CartItemDto;
import com.Manel_Backend.Dtos.PurchaseRequest;
import com.Manel_Backend.Models.Compra;
import com.Manel_Backend.Models.ItemCompra;
import com.Manel_Backend.Models.Product;
import com.Manel_Backend.Repository.OrderRepository;
import com.Manel_Backend.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Compra createOrder(PurchaseRequest request) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<ItemCompra> itemCompras = new ArrayList<>();

        // Validate products and calculate total
        for (CartItemDto item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }

            // Reduce stock
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProductId(product.getId());
            itemCompra.setQuantity(item.getQuantity());
            itemCompra.setPrice(product.getPrice());
            itemCompras.add(itemCompra);

            totalAmount = totalAmount.add(itemTotal);
        }

        // Create order
        Compra compra = new Compra();
        compra.setOrderDate(LocalDateTime.now());
        compra.setItems(itemCompras);
        compra.setTotalAmount(totalAmount);

        return orderRepository.save(compra);
    }
}
