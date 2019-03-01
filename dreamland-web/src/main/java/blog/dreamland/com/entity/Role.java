package blog.dreamland.com.entity;

import lombok.Data;

public @Data class Role {
    private Long id;

    private String roleName;

    private String roleValue;

    private String roleMatcher;

    private String enabled;

    private String remark;

}