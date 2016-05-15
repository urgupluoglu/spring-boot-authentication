package com.badboyz.dao;

import com.badboyz.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by yusuf on 4/26/2016.
 */

@Controller
public class UserDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    public User getUser(long userId) {
        return em.find(User.class, userId);
    }
}
