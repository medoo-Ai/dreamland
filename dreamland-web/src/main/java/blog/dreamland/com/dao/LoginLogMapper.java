package blog.dreamland.com.dao;

import blog.dreamland.com.entity.LoginLog;
import tk.mybatis.mapper.common.Mapper;

public interface LoginLogMapper extends Mapper<LoginLog> {
    int deleteByPrimaryKey(Long id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}