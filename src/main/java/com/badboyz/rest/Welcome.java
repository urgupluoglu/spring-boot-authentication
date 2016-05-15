package com.badboyz.rest;

import com.badboyz.dao.UserDao;
import com.badboyz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yusuf on 4/26/2016.
 */

@RestController
public class Welcome {

    @Autowired
    UserDao userDao;

    @RequestMapping("/")
    public String welcome() {
        /*User user = new User();
        user.setFirstName("Yusuf");
        user.setMiddleName("Togay");
        user.setLastName("Urgupluoglu");

        userDao.save(user);*/

        return "working!";
    }
}
