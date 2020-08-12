package com.martirosyan.p2hw7.controllers;


import com.martirosyan.p2hw7.model.Product;
import com.martirosyan.p2hw7.service.ProductService;
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

    @GetMapping("")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product",productService.findById(id));
        return "editproduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model){
        productService.delete(id);
        System.out.println("id");
        return "redirect:/products";
    }

    @PostMapping("")
    public String addProduct(@ModelAttribute Product newProduct) {
        productService.save(newProduct);
        return "redirect:/products";

    }
}