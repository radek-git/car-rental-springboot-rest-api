package com.radek.rentals.service;

import com.radek.rentals.dto.BrandDTO;
import com.radek.rentals.dto.ModelDTO;
import com.radek.rentals.entity.Brand;
import com.radek.rentals.entity.Model;
import com.radek.rentals.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ModelDTO convertModelToDTO(Model model) {
        return new ModelDTO(
                model.getName()
        );
    }

    public List<ModelDTO> convertModelListToDTO(List<Model> models) {
        return models.stream().map(this::convertModelToDTO).collect(Collectors.toList());
    }

    public ModelDTO findById(Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new RuntimeException("nie ma"));
        return convertModelToDTO(model);
    }

    public List<ModelDTO> findAll() {
        return convertModelListToDTO(modelRepository.findAll());
    }

    public ModelDTO findModelByLetter(Character letter) {
        Model model = modelRepository.findDistinctFirstByNameStartsWith(letter)
                .orElseThrow(() -> new RuntimeException("nie ma"));

        return convertModelToDTO(model);
    }

    public void deleteById(Long id) {
        modelRepository.deleteById(id);
    }

    public ModelDTO save(Model model) {
        return convertModelToDTO(modelRepository.save(model));
    }


}
