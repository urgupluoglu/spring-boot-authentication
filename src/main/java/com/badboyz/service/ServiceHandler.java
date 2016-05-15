package com.badboyz.service;

import com.badboyz.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by yusuf on 4/26/2016.
 */

@Controller
@Scope(value = "prototype")
public class ServiceHandler {

    @Autowired
    UserDao userDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    @Resource(name = "configurator")
    private Properties configurator;

    private static final Logger LOGGER = LogManager.getLogger();
}
