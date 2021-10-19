package com.Spring.stud.demo.dto;

import com.Spring.stud.demo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "Веедине название товара")
    @Length(min = 3, max =255, message = "Имя товара должно быть в переделах 3-255 символов")
    private String title;

    @Min(value = 1, message = "Цена товара не может быть ниже 1 руб.")
    private int price;

    @NotNull(message = "Введите категорию товара")
    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }

}
