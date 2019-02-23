package com.radek.rentals.service;

import com.radek.rentals.dto.RentalDTO;
import com.radek.rentals.entity.Car;
import com.radek.rentals.entity.Rental;
import com.radek.rentals.entity.User;
import com.radek.rentals.repository.CarRepository;
import com.radek.rentals.repository.RentalRepository;
import com.radek.rentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private CarRepository carRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }







    private RentalDTO convertRentalToDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCar().getId(),
                rental.getUser().getId(),
                rental.getStartTime(),
                rental.getEndTime(),
                rental.getTotalPrice()
        );
    }

    private List<RentalDTO> convertRentalsToDTO(List<Rental> rentals) {
        return rentals.stream().map(this::convertRentalToDTO).collect(Collectors.toList());
    }


    public RentalDTO findRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie ma takiej operacji"));

        return convertRentalToDTO(rental);
    }

    public List<RentalDTO> findall() {
        return convertRentalsToDTO(rentalRepository.findAll());
    }


//    public RentalDTO findTotalPriceMoreThan(BigDecimal totalPrice) {
//        Rental rental = rentalRepository.findRentalByTotalPriceLessThan(totalPrice).orElseThrow(() -> new RuntimeException("Brak operacji"));
//
//        return convertRentalToDTO(rental);
//    }


    public void deleteById(Long id) {
        rentalRepository.deleteById(id);
    }

    public RentalDTO saveRentalDTO(RentalDTO rentalDTO) {
        User user = userRepository.findById(rentalDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Nie ma takiego użytkownika"));
        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Nie ma"));

        Rental rental = new Rental(car, user,
                rentalDTO.getStartTime(),
                rentalDTO.getEndTime(),
                rentalDTO.getTotalPrice()
        );

        return convertRentalToDTO(rentalRepository.save(rental));
    }


}