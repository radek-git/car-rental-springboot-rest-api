package com.radek.rentals.controller;

import com.radek.rentals.dto.CarDTO;
import com.radek.rentals.entity.Car;
import com.radek.rentals.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cars/")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public List<CarDTO> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public CarDTO findById(@PathVariable Long id) {
        return carService.findById(id);
    }


}
