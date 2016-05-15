package com.badboyz.general;

import com.badboyz.Utility;
import com.badboyz.dao.UserDao;
import com.badboyz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yusuf on 5/15/16.
 */
public class Tokenizer {

    @Autowired
    UserDao userDao;

    public static ConcurrentHashMap<String, JWT> bucket = new ConcurrentHashMap<>();

    public synchronized User getUserByToken(String token) {
        JWT jwt = bucket.get(token);
        return userDao.getUser(jwt.getUserId());
    }

    public synchronized String generateToken(User user) {
        SecureRandom random = new SecureRandom();
        String token = (new BigInteger(130, random).toString(32) + new BigInteger(130, random).toString(32)).toUpperCase();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, Utility.TOKEN_EXPIRATION_IN_MINUTES); // adds minutes
        JWT jwt = new JWT();
        jwt.setCreate(new Date());
        jwt.setExpire(cal.getTime());
        jwt.setUserId(user.getUserId());
        bucket.keySet()
                .stream()
                .filter(item -> bucket.get(item).getUserId() == user.getUserId())
                .forEach(bucket::remove);
        bucket.put(token, jwt);

        return token;
    }

    public synchronized boolean removeUserByToken(String token) {
        if (bucket.containsKey(token)) {
            bucket.remove(token);
            return true;
        }
        return false;
    }
}
