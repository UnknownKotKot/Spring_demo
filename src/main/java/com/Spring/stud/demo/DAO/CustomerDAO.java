package com.Spring.stud.demo.DAO;

import com.Spring.stud.demo.api.IEntity;
import com.Spring.stud.demo.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO implements IEntity<Customer> {
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
            session.getTransaction().commit();
            return customers;
        }

    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Customer c where c.id= :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = new Customer(customer.getId(), customer.getName());
            session.saveOrUpdate(c);
            session.getTransaction().commit();
            return c;
        }
    }

    @Override
    public void addCost(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reduceCost(Long id) {
        throw new UnsupportedOperationException();
    }
}
