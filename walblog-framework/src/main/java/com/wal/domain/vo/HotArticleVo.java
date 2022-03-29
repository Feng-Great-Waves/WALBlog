package com.wal.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private long id;
    //标题
    private String title;
    //访问量
    private long viewCount;
}
