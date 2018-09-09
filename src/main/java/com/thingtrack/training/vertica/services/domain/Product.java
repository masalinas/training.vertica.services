package com.thingtrack.training.vertica.services.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import java.util.Date;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "product_dimension")
@ApiModel(description = "Class representing a product.")
public class Product implements Serializable {

    @Id
    @Column(name = "product_key")
    @ApiModelProperty(notes = "Key of the product. No two products can have the same id version.", example = "1", required = true, position = 0)
    private Long key;
    
    @Column(name = "product_description", nullable = true)
    @ApiModelProperty(notes = "Description of the product.", example = "Brand #4 brandy", required = false, position = 1)
    private String description;

    @Column(name = "product_price", nullable = true)
    @ApiModelProperty(notes = "Price of the product.", example = "5.4", required = true, position = 2)
    private float price;

    @Column(name = "discontinued_flag", nullable = true)
    @ApiModelProperty(notes = "Status of the product.", example = "true", required = true, position = 3)
    private boolean active = true;

    public Product() {        
    }

    public Product(String description, float price, boolean active, Date createdAt) {        
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Long getKey() {
        return key;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
