package com.radek.rentals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long brandId;
    private Long modelId;
    private Long typeId;
    private int year;
    private int mileage;
    private BigDecimal pricePerDay;
}
