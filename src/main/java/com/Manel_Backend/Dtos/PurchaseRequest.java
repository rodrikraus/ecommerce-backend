package com.Manel_Backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseRequest {
    private List<CartItemDto> items;

    public List<CartItemDto> getItems() {
        return items;
    }
}

