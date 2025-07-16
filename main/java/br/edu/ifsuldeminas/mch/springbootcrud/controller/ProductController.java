package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Product;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Category;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ProductRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product_list.html";
    }

    @GetMapping("/products/form")
    public String productForm(@ModelAttribute("product") Product product, Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "product_form.html";
    }

    @PostMapping("/products/new")
    public String productSave(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "product_form.html";
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String productUpdate(@PathVariable int id, Model model) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (!productOpt.isPresent())
            throw new IllegalArgumentException("Product with id " + id + " does not exist.");

        model.addAttribute("product", productOpt.get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form.html";
    }

    @GetMapping("/products/delete/{id}")
    public String productDelete(@PathVariable int id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (!productOpt.isPresent())
            throw new IllegalArgumentException("Product with id " + id + " does not exist.");

        productRepository.delete(productOpt.get());
        return "redirect:/products";
    }
}
