package blog.dreamland.com.entity;

import lombok.Data;

import java.util.Date;

public @Data class LoginLog {
    private Long id;

    private Long uId;

    private String ip;

    private Date createTime;
}