package com.leyou.item.service;

import com.leyou.item.dao.Category;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryService
 * @Description: 商品分类Service
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:20
 * @Version V1.0
 **/
public interface CategoryService {

    /**
     * @MethodName: queryCategoryListByParentId
     * @Description: 根据父Id查询商品分类列表
     * @param parentId
     * @Return: java.util.List<com.leyou.item.dao.Category>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/7 22:22
    **/
    List<Category> queryCategoryListByParentId(Long parentId);

    /**
     * @MethodName: queryCategoryListByBrandId
     * @Description: 根据品牌Id查询商品分类
     * @param brandId
     * @Return: java.util.List<com.leyou.item.dao.Category>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/10 23:04
     **/
    List<Category> queryCategoryListByBrandId(Long brandId);

    /**
     * @MethodName: queryCategoryNameMapByIdList
     * @Description: 根据Id列表得到分类名称Map
     * @param categoryIdList
     * @Return: java.util.Map<java.lang.Long,java.lang.String>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 23:17
    **/
    Map<Long, String> queryCategoryNameMapByIdList(List<Long> categoryIdList);
}
