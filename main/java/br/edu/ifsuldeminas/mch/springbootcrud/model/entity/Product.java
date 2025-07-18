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

    @NotBlank(message = "Nome não pode ser vazio.")
    private String name;

    @NotBlank(message = "Descrição não pode ser vazio.")
    private String description;

    @NotNull(message = "Proço não pode ser vazio ou zero")
    @DecimalMin(value = "0.01", message = "Preço não pode ser 0.0")
    private BigDecimal price;

    @NotNull(message = "Estoque não pode ser vazio ou zero.")
    @Min(value = 0, message = "Estoque não pode ser vazio ou zero.")
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
