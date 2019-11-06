package com.waiter.web.dao.mysql;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waiter.web.entity.mysql.Comment;
import org.apache.ibatis.annotations.Mapper;

@DS("slave1")
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
