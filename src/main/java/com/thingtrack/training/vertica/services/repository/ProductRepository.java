package com.thingtrack.training.vertica.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thingtrack.training.vertica.services.domain.Product;
import com.thingtrack.training.vertica.services.domain.ProductId;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {    
}