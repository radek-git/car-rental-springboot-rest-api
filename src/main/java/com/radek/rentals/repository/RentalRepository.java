package com.radek.rentals.repository;

import com.radek.rentals.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {


    List<Rental> findRentalByTotalPriceLessThan(BigDecimal totalPrice);

    List<Rental> findByUserAgeLessThan(int age);
}

