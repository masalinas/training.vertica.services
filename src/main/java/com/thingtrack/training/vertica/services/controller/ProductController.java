package com.thingtrack.training.vertica.services.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.thingtrack.training.vertica.services.domain.Product;
import com.thingtrack.training.vertica.services.domain.ProductId;
import com.thingtrack.training.vertica.services.exception.ResourceNotFoundException;
import com.thingtrack.training.vertica.services.repository.ProductRepository;

@RestController
@RequestMapping("/api")
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Products.")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    @ApiOperation("Returns list of all Products in the system.")
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products;
    }

    @GetMapping("/products/{key}/{version}")
    @ApiOperation("Returns a specific product by their Id (Key, Version). 404 if does not exist.")
    public Product getProductById(@PathVariable(value = "key") Long productKey, @PathVariable(value = "version") Long productVersion) {
        return productRepository.findById(new ProductId(productKey, productVersion))
               .orElseThrow(() -> new ResourceNotFoundException("Product", "key", productKey));
    }
    
    @PostMapping("/products")
    @ApiOperation("Creates a new product.")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{key}/{version}")
    @ApiOperation("Updates a product by Id (Key, Version) from the system. 404 if the product's identifier is not found.")
    public Product updateProduct(@Valid @RequestBody Product productDetails) {

        Product product = productRepository.findById(productDetails.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productDetails.getId()));              

        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setActive(productDetails.isActive());

        Product updatedProduct = productRepository.save(product);

        return updatedProduct;
    }

    @DeleteMapping("/products/{key}/{version}")
    @ApiOperation("Deletes a product by Id (Key, Version) from the system. 404 if the product's identifier is not found.")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "key") Long productKey,
    									   @PathVariable(value = "version") Long productVersion) {
        Product product = productRepository.findById(new ProductId(productKey, productVersion))
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", new ProductId(productKey, productVersion)));
                
        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}