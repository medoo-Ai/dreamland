package blog.dreamland.com.service.impl;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.entity.Comment;
import blog.dreamland.com.entity.UserContent;
import blog.dreamland.com.service.UserContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther SyntacticSugar
 * @data 2019/3/1 0001下午 3:56
 */
@Service
public class UserContentServiceImpl implements UserContentService {

    // todo
    @Override
    public PageHelper.Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize) {

        return null;
    }

    @Override
    public PageHelper.Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageHelper.Page<UserContent> findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<UserContent> findByUserId(Long uid) {
        return null;
    }

    @Override
    public List<UserContent> findAll() {
        return null;
    }
}
