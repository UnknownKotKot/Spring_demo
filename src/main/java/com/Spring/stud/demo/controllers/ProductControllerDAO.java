package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.ProductServiceDAO;
import com.Spring.stud.demo.services.ProductServiceInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductControllerDAO {
    private ProductServiceDAO productService;

    @Autowired
    public ProductControllerDAO(ProductServiceDAO productService) {
        this.productService = productService;
    }

    @GetMapping("/catalogue")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productCatalogue";
    }

    @GetMapping("/product/{id}")
    public String getProductInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
        return "productInfo";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        Product product = new Product(id, title, cost);
        productService.saveOrUpdate(product);
        return "redirect:/catalogue";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "productAdd";
    }

    @GetMapping("/delete/{id}")
    public String delProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/catalogue";
    }

}
