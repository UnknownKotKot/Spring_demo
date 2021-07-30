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
import org.thymeleaf.util.StringUtils;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalogue")
    public String showAllGoods(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product_list";
    }

    @GetMapping("/article/{id}")
    public String getArticleInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getById(id));
        return "articleAbout";
    }

    @PostMapping("/add")
    public String addArticle(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        Product product = new Product(id, title, cost);
        productService.save(product);
        return "redirect:/catalogue";
    }

    @GetMapping("/add")
    public String addArticle() {
        return "addArticle";
    }

}
