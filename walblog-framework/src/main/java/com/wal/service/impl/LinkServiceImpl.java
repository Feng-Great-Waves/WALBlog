package com.wal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.constants.SystemConstants;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Link;
import com.wal.domain.vo.LinkVo;
import com.wal.mapper.LinkMapper;
import com.wal.service.LinkService;
import com.wal.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;


/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-03-21 22:56:45
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        return ResponseResult
                .okResult(BeanCopyUtils
                        .copyBeanList(list(new LambdaQueryWrapper<Link>()
                                .eq(Link::getStatus
                                        , SystemConstants.LINK_STATUS_NORMAL))
                                ,LinkVo.class));
    }
}
