package com.leyou.item.service.impl;

import com.leyou.item.dao.Category;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description: 商品分类接口实现类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:23
 * @Version V1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByParentId(Long parentId) {
        Category record = new Category();
        record.setParentId(parentId);
        return categoryMapper.select(record);
    }
}
