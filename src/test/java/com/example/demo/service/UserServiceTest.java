package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.domain.UserDomain;
import com.example.demo.service.excel.ExcelReadService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserServiceTest.java, V 0.1 2019/5/31 下午2:00 wanggengen Exp $$
 **/
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Resource
    private ExcelReadService excelReadService;

    @Test
    public void testFindAllUser() {
        PageInfo<UserDomain> allUser = userService.findAllUser(1, 10);

        System.out.println("end");
    }

    @Test
    public void testUserDownload() {

        excelReadService.userDownload();
    }


}
