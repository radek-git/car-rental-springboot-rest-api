package com.radek.rentals.controller;


import com.radek.rentals.dto.ModelDTO;
import com.radek.rentals.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    private ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/all")
    public List<ModelDTO> findAll() {
        return modelService.findAll();
    }

    @GetMapping("/all/{letter}")
    public ModelDTO findByLetter(@PathVariable Character letter) {
        return modelService.findModelByLetter(letter);
    }

}
