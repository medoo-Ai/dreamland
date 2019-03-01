package blog.dreamland.com.dao;

import blog.dreamland.com.entity.Upvote;
import tk.mybatis.mapper.common.Mapper;

public interface UpvoteMapper extends Mapper<Upvote> {
    int deleteByPrimaryKey(Long id);

    int insert(Upvote record);

    int insertSelective(Upvote record);

    Upvote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Upvote record);

    int updateByPrimaryKey(Upvote record);
}