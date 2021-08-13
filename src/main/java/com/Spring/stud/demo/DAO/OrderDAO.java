package com.Spring.stud.demo.DAO;

import com.Spring.stud.demo.model.Customer;
import com.Spring.stud.demo.model.Order;
import com.Spring.stud.demo.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void getProductsByCustomerId(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            for (Order o : customer.getOrders()) {
                System.out.println(o.getProduct().getTitle());
            }
            session.getTransaction().commit();
        }
    }

    public void getCustomersByProductId(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            for (Order o : product.getOrders()) {
                System.out.println(o.getCustomer().getName());
            }
            session.getTransaction().commit();
        }
    }

    public void getPrice(Long customerId, Long prodId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            for (Order o : customer.getOrders()) {
                if (o.getProduct().getId().equals(prodId)) {
                    System.out.println(o.getProduct().getCost());
                }
            }
            session.getTransaction().commit();
        }

    }
}
