package com.example.demo.disconf.dao.mapper;

import com.example.demo.disconf.DemoDisconfApplicationTests;
import com.example.demo.disconf.dao.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author xiangzhurui
 * @version 2018/8/10 10:54
 */
public class UserInfoMapperTest extends DemoDisconfApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        UserInfo userInfo = new UserInfo() {{
            setName("12321");
            setGender("F");
            setCreateAt(LocalDateTime.now());
            setModifyAt(LocalDateTime.now());
            setCreateBy("test");
            setModifyBy("test");
        }};
        int i = userInfoMapper.insert(userInfo);
        System.out.println(i);
        assertEquals(1, i);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}