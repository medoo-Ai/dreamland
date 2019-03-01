package blog.dreamland.com.dao;

import blog.dreamland.com.entity.UserContent;
import tk.mybatis.mapper.common.Mapper;

public interface UserContentMapper extends Mapper<UserContent> {
    int deleteByPrimaryKey(Long id);

    int insert(UserContent record);

    int insertSelective(UserContent record);

    UserContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserContent record);

    int updateByPrimaryKeyWithBLOBs(UserContent record);

    int updateByPrimaryKey(UserContent record);
}