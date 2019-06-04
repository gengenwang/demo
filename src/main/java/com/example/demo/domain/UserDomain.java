package com.example.demo.domain;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: UserDomain.java, V 0.1 2019/5/31 上午10:57 wanggengen Exp $$
 **/
public class UserDomain implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}