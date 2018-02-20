package org.cool.zoo.entities.core;

import javax.persistence.*;

@Entity
@Table(name="tbl_product")
public class Product {

    private Long id;
    private String productName;
    private String productCode;
    private String productDesc;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pro_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "pro_name", nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "pro_code", nullable = false)
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "pro_dec")
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
