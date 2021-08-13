package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalogue")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "productCatalogue";
    }

    @GetMapping("/product/{id}")
    public String getProductInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getById(id));
        return "productInfo";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        Product product = new Product(id, title, cost);
        productService.save(product);
        return "redirect:/catalogue";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "addProduct";
    }

    @GetMapping("/addCost/{id}")
    public String addCost(@PathVariable Long id) {
        productService.addCost(id);
        return "redirect:/product/{id}";
    }

    @GetMapping("/reduceCost/{id}")
    public String reduceCost(@PathVariable Long id) {
        productService.reduceCost(id);
        return "redirect:/product/{id}";
    }

}
