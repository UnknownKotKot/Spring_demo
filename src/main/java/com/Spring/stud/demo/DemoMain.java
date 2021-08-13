package com.Spring.stud.demo;

import com.Spring.stud.demo.DAO.OrderDAO;
import com.Spring.stud.demo.services.OrderService;
import org.hibernate.cfg.Configuration;

public class DemoMain {
    public static void main(String[] args) {
        OrderService orderService = new OrderService(new OrderDAO(new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()));
        orderService.getProductsByCustomerId(1L);
        orderService.getCustomersByProductId(3L);
        orderService.getPrice(1L, 3L);
    }
}
