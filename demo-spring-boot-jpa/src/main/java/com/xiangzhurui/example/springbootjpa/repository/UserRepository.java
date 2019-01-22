package com.xiangzhurui.example.springbootjpa.repository;

import com.xiangzhurui.example.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiangzhurui
 * @version 2019-01-21 11:37
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


    User findFirstByOrderByCreateTimeDesc();
}
