package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Product name cannot be blank.")
    private String name;

    @NotBlank(message = "Product description cannot be blank.")
    private String description;

    @NotNull(message = "Price cannot be null.")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null.")
    @Min(value = 0, message = "Stock must be zero or greater.")
    private Integer stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    @Valid
    private Category category;

    public Product() {
        setName("");
        setDescription("");
        setPrice(BigDecimal.ZERO);
        setStock(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
