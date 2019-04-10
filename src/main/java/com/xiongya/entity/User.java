package com.xiongya.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @Author xiongzhilong
 * @Email 2584496774@qq.com
 * @Date create by 2019-04-04 14:58
 */
@Data
public class User {

    private String userName;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    public User(String userName, String password){
        super();
        this.userName = userName;
        this.password = password;
    }

    public User(){
        super();
    }

}
