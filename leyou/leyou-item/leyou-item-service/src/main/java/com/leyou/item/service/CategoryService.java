package com.leyou.item.service;

import com.leyou.item.dao.Category;

import java.util.List;

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
}
