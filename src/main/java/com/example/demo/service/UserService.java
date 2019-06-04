package com.example.demo.service;

import com.example.demo.domain.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserExcelService.java, V 0.1 2019/5/31 上午10:55 wanggengen Exp $$
 **/
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
