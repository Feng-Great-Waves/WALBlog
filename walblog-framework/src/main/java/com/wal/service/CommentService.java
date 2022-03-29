package com.wal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author fwt
 * @since 2022-03-23 13:14:15
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
