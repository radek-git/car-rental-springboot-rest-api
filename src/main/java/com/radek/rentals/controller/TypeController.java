package com.radek.rentals.controller;

import com.radek.rentals.dto.TypeDTO;
import com.radek.rentals.repository.TypeRepository;
import com.radek.rentals.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }




    @GetMapping("/all")
    public List<TypeDTO> findAll() {
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    public TypeDTO findById(@PathVariable Long id) {
        return typeService.findById(id);
    }

}
