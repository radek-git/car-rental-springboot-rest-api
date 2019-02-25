package com.radek.rentals.controller;

import com.radek.rentals.dto.BrandDTO;
import com.radek.rentals.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/{id}")
    public BrandDTO findById(@PathVariable Long id) {
        return brandService.findBandById(id);
    }

    @GetMapping("/all")
    public List<BrandDTO> findAll() {
        return brandService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
    }


}
