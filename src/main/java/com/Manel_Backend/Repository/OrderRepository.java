package com.Manel_Backend.Repository;

import com.Manel_Backend.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

