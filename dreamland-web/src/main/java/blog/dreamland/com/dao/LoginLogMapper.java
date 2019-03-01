package blog.dreamland.com.dao;

import blog.dreamland.com.entity.LoginLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface LoginLogMapper extends Mapper<LoginLog>{

}