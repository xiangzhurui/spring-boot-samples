package com.xiangzhurui.example.springbootjpa.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiangzhurui.example.springbootjpa.DemoSpringBootJpaApplicationTests;
import com.xiangzhurui.example.springbootjpa.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiangzhurui
 * @version 2019-01-21 11:39
 */
public class UserRepositoryTest extends DemoSpringBootJpaApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findFirstByOrderByCreateTimeDescTest() {
        User topOrderByCreateTime = userRepository.findFirstByOrderByCreateTimeDesc();
//        System.out.println(topOrderByCreateTime);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String x = gson.toJson(topOrderByCreateTime);
        System.out.println(x);

    }

    @Test
    public void saveTest() {
        User entity = new User();
        entity.setUsername("test1");
        entity.setPassword("test1");
        User save = userRepository.save(entity);
        System.out.println(entity);
        System.out.println(save);
    }
}