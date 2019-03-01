package blog.dreamland.com.service;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.entity.Comment;
import blog.dreamland.com.entity.UserContent;

public interface UserContentService {

    PageHelper.Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize);
    PageHelper.Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize);
    PageHelper.Page<UserContent> findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize);
}
