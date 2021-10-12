package com.Spring.stud.demo;

import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.CartService;
import com.Spring.stud.demo.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void initCart() {
        cartService.clearCart();
    }

    @Test
    public void addToCartTest() {
        Product product = new Product();
        product.setId(10L);
        product.setTitle("test_product_1");
        product.setPrice(999);

        Category category = new Category();
        category.setId(10L);
        category.setTitle("test_category_1");

        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(10L);
        cartService.addProduct(10L);
        cartService.addProduct(10L);
        cartService.addProduct(10L);
        Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
        Assertions.assertEquals(1, cartService.getCartForCurrentUser().getOrderItems().size());
    }
}
