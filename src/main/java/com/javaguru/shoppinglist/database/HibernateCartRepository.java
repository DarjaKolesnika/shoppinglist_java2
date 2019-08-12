package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

@Repository
@Transactional
@Profile("hibernate")
public class HibernateCartRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    HibernateCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Cart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart.getId();
    }

    public void update(Cart cart) {
        sessionFactory.getCurrentSession().saveOrUpdate(cart);
    }


    public Optional<Cart> findCartById(Long id) {
        Cart cart = (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(cart);
    }

    public boolean cartExistsByName(String name) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Cart c where c.name='" + name + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setMaxResults(1)
                .uniqueResult();
    }

    public void delete(Cart cart) {
        sessionFactory.getCurrentSession().delete(cart);
    }
}
