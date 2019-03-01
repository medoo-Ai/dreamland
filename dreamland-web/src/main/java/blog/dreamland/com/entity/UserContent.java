package blog.dreamland.com.entity;

import lombok.Data;

import java.util.Date;

public @Data class UserContent {
    private Long id;

    private Long uId;

    private String title;

    private String category;

    private String personal;

    private Date rptTime;

    private String imgUrl;

    private String nickName;

    private Integer upvote;

    private Integer downvote;

    private Integer commentNum;

    private String content;

}