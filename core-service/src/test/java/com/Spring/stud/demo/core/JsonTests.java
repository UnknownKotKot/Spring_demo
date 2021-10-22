package com.Spring.stud.demo.core;

import com.Spring.stud.demo.api.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<ProductDto> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setCategoryTitle("testCategory");
        productDto.setPrice(100);
        productDto.setTitle("testProdDto");
        // {
        //   "id": 1,
        //   "title": "testProdDto",
        //   "price": 100,
        //   "categoryTitle": "testCategory",
        // }

        assertThat(jackson.write(productDto))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("testProdDto");

        assertThat(jackson.write(productDto))
                .hasJsonPathNumberValue("$.price")
                .extractingJsonPathStringValue("$.categoryTitle").isEqualTo("testCategory");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"testProdDto2\",\"price\": 1002,\"categoryTitle\":\"testCategory2\"}";
        ProductDto realProductDto = new ProductDto();
        realProductDto.setId(2L);
        realProductDto.setCategoryTitle("testCategory2");
        realProductDto.setPrice(1002);
        realProductDto.setTitle("testProdDto2");

        assertThat(jackson.parse(content)).isEqualTo(realProductDto);
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("testProdDto2");
    }

}
