package blog.dreamland.com.entity;

import lombok.Data;

import java.util.Date;

public @Data class UserInfo {
    private Long id;

    private Long uId;

    private String name;

    private String sex;

    private Date birthday;

    private String hobby;

    private String address;

}