package com.radek.rentals.service;

import com.radek.rentals.dto.RentalDTO;
import com.radek.rentals.dto.UserDTO;
import com.radek.rentals.entity.Rental;
import com.radek.rentals.entity.User;
import com.radek.rentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return convertToDTO(userRepository.findAll());
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user"));

        System.out.println(user.getRentals().size());

        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getAge(),
                convertToRentalsDTO(user.getRentals()));
    }

    private RentalDTO convertToRentalDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCar().getId(),
                rental.getUser().getId(),
                rental.getStartTime(),
                rental.getEndTime(),
                rental.getTotalPrice()
        );
    }

    private List<RentalDTO> convertToRentalsDTO(List<Rental> rentals) {
        return rentals.stream().map(this::convertToRentalDTO).collect(Collectors.toList());
    }

    private List<UserDTO> convertToDTO(List<User> users) {
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public UserDTO save(User user) {
        return convertToDTO(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO updateUserById(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user"));

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }

        if (userDTO.getSurname() != null) {
            user.setSurname(userDTO.getSurname());
        }

        if (userDTO.getAge() != 0) {
            user.setAge(userDTO.getAge());
        }

        userRepository.save(user);

        return convertToDTO(user);
    }



    public List<RentalDTO> rentalDTOListByUserId(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("nie ma"));

        return convertToRentalsDTO(user.getRentals());
    }
}

