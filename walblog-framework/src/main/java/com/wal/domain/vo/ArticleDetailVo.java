package com.wal.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.function.LongFunction;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private long id;
    //标题
    private String title;
    //文章摘要
    private String summary;

    //所属分类id
    private long categoryId;
    //文章内容
    private String content;
    //所属分类名称
    private String categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;

    private Date createTime;
}
