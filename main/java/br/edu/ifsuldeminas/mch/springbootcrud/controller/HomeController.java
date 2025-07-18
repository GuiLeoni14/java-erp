package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ProductRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CategoryRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("productCount", productRepository.count());
        model.addAttribute("categoryCount", categoryRepository.count());
        return "index.html";
    }
}