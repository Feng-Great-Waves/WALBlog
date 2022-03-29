package com.wal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.constants.SystemConstants;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Comment;
import com.wal.domain.vo.CommentVo;
import com.wal.domain.vo.PageVo;
import com.wal.enums.AppHttpCodeEnum;
import com.wal.exception.SystemException;
import com.wal.mapper.CommentMapper;
import com.wal.service.CommentService;
import com.wal.service.UserService;
import com.wal.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author fwt
 * @since 2022-03-23 13:14:15
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page,new LambdaQueryWrapper<Comment>()
                .eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId)
                .eq(Comment::getRootId, SystemConstants.ROOT_ID)
                .eq(Comment::getType,commentType));

        List<CommentVo> commentVos = toCommentList(page.getRecords());

        for (CommentVo commentVo : commentVos) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }


    /**
     * 根据根评论id查询所有子评论
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        List<Comment> list = list(new LambdaQueryWrapper<Comment>()
                .eq(Comment::getRootId, id)
                .orderByAsc(Comment::getCreateTime));
        List<CommentVo> commentVos = toCommentList(list);
        return commentVos;
    }

    private List<CommentVo> toCommentList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            String username = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(username);

            if(commentVo.getRootId()!=-1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
