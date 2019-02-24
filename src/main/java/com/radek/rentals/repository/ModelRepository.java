package com.radek.rentals.repository;

import com.radek.rentals.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findDistinctFirstByNameStartsWith(Character letter);
}
