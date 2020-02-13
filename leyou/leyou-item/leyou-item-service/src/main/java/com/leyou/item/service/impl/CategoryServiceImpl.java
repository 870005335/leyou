package com.leyou.item.service.impl;

import com.leyou.item.dao.Category;
import com.leyou.item.dao.CategoryBrand;
import com.leyou.item.mapper.CategoryBrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CategoryServiceImpl
 * @Description: 商品分类接口实现类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:23
 * @Version V1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private CategoryBrandMapper categoryBrandMapper;

    public List<Category> queryCategoryListByParentId(Long parentId) {
        Category record = new Category();
        record.setParentId(parentId);
        return categoryMapper.select(record);
    }

    @Override
    public List<Category> queryCategoryListByBrandId(Long brandId) {
        //根据品牌Id查询分类-品牌中间表
        Example example = new Example(CategoryBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("brandId", brandId);
        List<CategoryBrand> categoryBrandList = categoryBrandMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(categoryBrandList)) {
            //取出分类Id列表
            List<Long> categoryIdList = categoryBrandList.stream().
                    map(CategoryBrand::getCategoryId).collect(Collectors.toList());
            //查询分类列表
            Example categoryExample = new Example(Category.class);
            Example.Criteria categoryCriteria = categoryExample.createCriteria();
            categoryCriteria.andIn("id", categoryIdList);
            return categoryMapper.selectByExample(categoryExample);
        }
        return null;
    }
}
