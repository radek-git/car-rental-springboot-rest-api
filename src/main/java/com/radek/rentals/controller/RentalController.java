package com.radek.rentals.controller;

import com.radek.rentals.dto.RentalDTO;
import com.radek.rentals.entity.Rental;
import com.radek.rentals.repository.RentalRepository;
import com.radek.rentals.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private RentalService rentalService;
    private RentalRepository rentalRepository;

    @Autowired
    public RentalController(RentalService rentalService, RentalRepository rentalRepository) {
        this.rentalService = rentalService;
        this.rentalRepository = rentalRepository;
    }



    @GetMapping("/all")
    public List<RentalDTO> getAllRentals() {
        return rentalService.findall();
    }

    @GetMapping("/{id}")
    public RentalDTO findById(@PathVariable Long id) {
        return rentalService.findRentalById(id);
    }

    @GetMapping("/prices/{price}")
    public List<RentalDTO> findPriceMoreThan (@PathVariable BigDecimal price) {
        return rentalService.findTotalPriceMoreThan(price);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {

        rentalService.deleteById(id);
    }

    @PostMapping
    public RentalDTO saveRental(@RequestBody RentalDTO rentalDTO) {
        return rentalService.saveRentalDTO(rentalDTO);
    }

//    @GetMapping("/all")
//    public List<Rental> get() {
////        return rentalRepository.findAll();
//        return rentalRepository.findByUserAgeLessThan(30);
//    }


}
