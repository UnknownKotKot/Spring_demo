package com.Spring.stud.demo;

import com.Spring.stud.demo.api.ProductRepository;
import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepoTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void prodRepoTest() {
        Product product = new Product();
        product.setTitle("test_product_1");
        product.setPrice(999);

        Category category = new Category();
        category.setTitle("test_category_1");

        product.setCategory(category);

        testEntityManager.persist(product);
        testEntityManager.persist(category);
        testEntityManager.flush();

        List<Product> productList = productRepository.findAll();

        Assertions.assertEquals(4, productList.size());
        Assertions.assertEquals("test_product_2", productList.get(1).getTitle());
        Assertions.assertEquals("test_category_1", productList.get(0).getCategory().getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(3, productList.size());
    }
}
