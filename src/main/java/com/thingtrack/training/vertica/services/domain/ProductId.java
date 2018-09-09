package com.thingtrack.training.vertica.services.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProductId implements Serializable {

	@Column(name = "product_key")
    private Long productKey;
 
    @Column(name = "product_version")
    private Long productVersion;
 
    public ProductId() {
    }
 
    public ProductId(Long productKey, Long productVersion) {
        this.productKey = productKey;
        this.productVersion = productVersion;
    }
 
    public Long getProductKey() {
        return productKey;
    }
 
    public Long getProductVersion() {
        return productVersion;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductId)) return false;
        ProductId that = (ProductId) o;
        return Objects.equals(getProductKey(), that.getProductKey()) &&
               Objects.equals(getProductVersion(), that.getProductVersion());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getProductKey(), getProductVersion());
    }
}
