package blog.dreamland.com.entity;

import lombok.Data;

public @Data class Resource {
    private Long id;
    private String name;
    private String url;
    private String enabled;
    private String remark;
}