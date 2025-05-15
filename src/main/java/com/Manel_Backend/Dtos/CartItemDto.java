package com.Manel_Backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
    private Long productId;
    private Integer quantity;
}
