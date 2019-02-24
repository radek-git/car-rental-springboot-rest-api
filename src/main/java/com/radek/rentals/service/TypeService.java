package com.radek.rentals.service;

import com.radek.rentals.dto.TypeDTO;
import com.radek.rentals.entity.Type;
import com.radek.rentals.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public TypeDTO convertTypeToDTO(Type type) {
        return new TypeDTO(
                type.getName()
        );
    }

    public List<TypeDTO> convertTypeListToDTO(List<Type> types) {
        return types.stream().map(this::convertTypeToDTO).collect(Collectors.toList());
    }


    public List<TypeDTO> findAll() {
        return convertTypeListToDTO(typeRepository.findAll());
    }

    public TypeDTO findById(Long id) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new RuntimeException("nie ma"));

        return convertTypeToDTO(type);
    }

    public TypeDTO save(Type type) {
        return convertTypeToDTO(typeRepository.save(type));
    }



}
