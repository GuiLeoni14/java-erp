package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Category name cannot be blank.")
    private String name;

    @NotBlank(message = "Category description cannot be blank.")
    private String description;

    @NotBlank(message = "Category code cannot be blank.")
    @Column(unique = true)
    private String code;

    public Category() {
        setName("");
        setDescription("");
        setCode("");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
