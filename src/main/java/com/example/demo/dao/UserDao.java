package com.example.demo.dao;

import com.example.demo.domain.UserDomain;

import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserDao.java, V 0.1 2019/5/31 上午11:00 wanggengen Exp $$
 **/
public interface UserDao {


    int insert(UserDomain record);


    List<UserDomain> selectUsers();

}
