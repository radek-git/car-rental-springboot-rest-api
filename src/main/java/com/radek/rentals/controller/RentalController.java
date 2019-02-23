package com.radek.rentals.controller;

import com.radek.rentals.dto.RentalDTO;
import com.radek.rentals.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/all")
    public List<RentalDTO> getAllRentals() {
        return rentalService.findall();
    }

    @GetMapping("/{id}")
    public RentalDTO findById(@PathVariable Long id) {
        return rentalService.findRentalById(id);
    }

//    @GetMapping("/{price}")
//    public RentalDTO findPriceMoreThan (BigDecimal price) {
//        return rentalService.findTotalPriceMoreThan(price);
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {

        rentalService.deleteById(id);
    }

    @PostMapping
    public RentalDTO saveRental(@RequestBody RentalDTO rentalDTO) {
        return rentalService.saveRentalDTO(rentalDTO);
    }

}
