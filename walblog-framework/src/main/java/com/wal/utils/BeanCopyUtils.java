package com.wal.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    //单个
    public static<V> V copyBean(Object source,Class<V> clazz) {

        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //列表
    public static<O,V> List<V> copyBeanList(List<O> sourceList,Class<V> clazz){
        return sourceList.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
