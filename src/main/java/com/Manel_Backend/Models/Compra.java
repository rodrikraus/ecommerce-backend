package com.Manel_Backend.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
