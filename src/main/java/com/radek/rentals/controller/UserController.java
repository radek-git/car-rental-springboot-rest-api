package com.radek.rentals.controller;

import com.radek.rentals.dto.RentalDTO;
import com.radek.rentals.dto.UserDTO;
import com.radek.rentals.entity.User;
import com.radek.rentals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public UserDTO updateUserById(@PathVariable Long id, @RequestBody UserDTO user) {
        return userService.updateUserById(id, user);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "n", required = false, defaultValue = "no-name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/{id}/rentals")
    public List<RentalDTO> rentalsById(@PathVariable Long id) {
        return userService.rentalDTOListByUserId(id);
    }

}