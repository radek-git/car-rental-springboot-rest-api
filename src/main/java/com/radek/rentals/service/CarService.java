package com.radek.rentals.service;

import com.radek.rentals.dto.CarDTO;
import com.radek.rentals.entity.Car;
import com.radek.rentals.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO convertCarToDTO(Car car) {
        return new CarDTO(
                car.getBrand().getId(),
                car.getModel().getId(),
                car.getType().getId(),
                car.getYear(),
                car.getMileage(),
                car.getPricePerDay()
        );
    }

    public List<CarDTO> convertCarListToDTO(List<Car> cars) {
        return cars.stream().map(this::convertCarToDTO).collect(Collectors.toList());
    }

    public CarDTO findById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("nie ma"));
        return convertCarToDTO(car);
    }

    public List<CarDTO> findAll() {
        return convertCarListToDTO(carRepository.findAll());
    }

}