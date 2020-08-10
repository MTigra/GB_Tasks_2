package com.martirosyan.p2hw6.controllers;

import com.martirosyan.p2hw6.model.Product;
import com.martirosyan.p2hw6.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        return null;
    }

    @PostMapping()
    public String addProduct(@ModelAttribute Product newProduct) {
        productService.save(newProduct);
        return "redirect:/products";

    }
}
