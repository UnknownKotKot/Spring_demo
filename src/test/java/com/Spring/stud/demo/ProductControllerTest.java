package com.Spring.stud.demo;

import com.Spring.stud.demo.api.ProductRepository;
import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProductsTest() throws Exception{

        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setTitle("test_product_test1");
        productDto.setPrice(999);
        productDto.setCategoryTitle("test_category_test1");
        Product product = new Product();
        List<ProductDto> allProds = new ArrayList<>(Arrays.asList(
                productDto
        ));
        Page<ProductDto> pageList = new PageImpl<>(allProds);

        given(productService.findAll(0, 10)).willReturn(pageList);

        mockMvc
                .perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].title", is(allProds.get(0).getTitle())));
    }
}
