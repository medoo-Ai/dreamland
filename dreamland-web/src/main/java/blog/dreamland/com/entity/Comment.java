package blog.dreamland.com.entity;

import lombok.Data;

import java.util.Date;

public @Data class Comment {
    private Long id;

    private Long conId;

    private Long comId;

    private Long byId;

    private Date commTime;

    private String children;

    private Integer upvote;

    private String comContent;
}