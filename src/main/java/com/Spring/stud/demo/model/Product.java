package com.Spring.stud.demo.model;

/*
1. Разобраться с примером проекта на Spring MVC;
2. Создать класс Товар (Product), с полями id, title, cost;
3. Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
 Репозиторий должен уметь выдавать список всех товаров и товар по id;
4. Сделать форму для добавления товара в репозиторий и логику работы этой формы;
5. Сделать страницу, на которой отображаются все товары из репозитория.
*/

public class Product {
    private Long id;
    private String title;
    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Product() {
    }

    public Product(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}


