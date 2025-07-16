package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Category;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category_list.html";
    }

    @GetMapping("/categories/form")
    public String categoryForm(@ModelAttribute("category") Category category) {
        return "category_form.html";
    }

    @PostMapping("/categories/new")
    public String categorySave(@Valid @ModelAttribute("category") Category category,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "category_form.html";
        }

        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String categoryUpdate(@PathVariable int id, Model model) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);

        if (!categoryOpt.isPresent())
            throw new IllegalArgumentException("Category with id " + id + " does not exist.");

        model.addAttribute("category", categoryOpt.get());
        return "category_form.html";
    }

    @GetMapping("/categories/delete/{id}")
    public String categoryDelete(@PathVariable int id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);

        if (!categoryOpt.isPresent())
            throw new IllegalArgumentException("Category with id " + id + " does not exist.");

        categoryRepository.delete(categoryOpt.get());
        return "redirect:/categories";
    }
}
