package space.generals.java;

import java.io.Serializable;

/*
* 继承这个接口该类才能被序列化, 才能被写入到 redis 中.
* */
public class UserInfo implements Serializable {
    private String name;
    private String lastLogin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
