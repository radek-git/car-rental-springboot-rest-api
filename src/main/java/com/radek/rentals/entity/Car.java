package com.radek.rentals.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Type type;

    private int year;
    private int mileage;
    private BigDecimal pricePerDay;
}