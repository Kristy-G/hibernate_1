package com.example.hibernate_1.dao;

import com.example.hibernate_1.model.Product;
import com.example.hibernate_1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public Product findById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Product> products = session.createQuery("from Product").list();
        session.close();
        return products;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        tx1.commit();
        session.close();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(product);
        tx1.commit();
        session.close();
        return product;
    }
}
