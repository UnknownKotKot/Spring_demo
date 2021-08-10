package com.Spring.stud.demo.repositories;

import com.Spring.stud.demo.api.IRepository;
import com.Spring.stud.demo.model.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryDAO implements IRepository<Product> {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product findById(Long id) {
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product", Product.class).getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Product prod = new Product(product.getId(), product.getTitle(), product.getCost());
            session.save(prod);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void save(Product product) {
        throw new UnsupportedOperationException();
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
