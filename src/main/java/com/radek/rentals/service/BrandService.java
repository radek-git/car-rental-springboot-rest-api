package com.radek.rentals.service;

import com.radek.rentals.dto.BrandDTO;
import com.radek.rentals.entity.Brand;
import com.radek.rentals.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public BrandDTO convertBrandToDTO(Brand brand) {
        return new BrandDTO(
                brand.getName()
        );
    }


    public List<BrandDTO> convertBrandListToDTO(List<Brand> brands) {
        return brands.stream().map(this::convertBrandToDTO).collect(Collectors.toList());
    }




    public List<BrandDTO> findAll() {
        return convertBrandListToDTO(brandRepository.findAll());
    }

    public BrandDTO findBandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("nie ma"));

        return convertBrandToDTO(brand);
    }

    public BrandDTO updateBrandById(String name, BrandDTO brandDTO) {
        Brand brand = brandRepository.findByName(name).orElseThrow(() -> new RuntimeException("nie ma"));

        if (brandDTO.getName() != null) {
            brand.setName(brandDTO.getName());
        }

        brandRepository.save(brand);

        return convertBrandToDTO(brand);
    }

    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
