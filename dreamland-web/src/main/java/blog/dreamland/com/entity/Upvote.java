package blog.dreamland.com.entity;

import lombok.Data;

import java.util.Date;

public @Data class Upvote {
    private Long id;

    private Long uId;

    private Long contentId;

    private String ip;

    private String upvote;

    private String downvote;

    private Date upvoteTime;

}