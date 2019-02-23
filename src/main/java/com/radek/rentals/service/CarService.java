package com.radek.rentals.service;

import com.radek.rentals.entity.Car;
import com.radek.rentals.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie ma takiego"));
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}