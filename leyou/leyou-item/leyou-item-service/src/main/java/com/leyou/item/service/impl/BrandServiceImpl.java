package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Brand;
import com.leyou.item.dao.Category;
import com.leyou.item.dao.CategoryBrand;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryBrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName BrandServiceImpl
 * @Description: 乐优品牌接口实现类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 17:04
 * @Version V1.0
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private BrandMapper brandMapper;

    @Autowired(required = false)
    private CategoryBrandMapper categoryBrandMapper;

    public PageResultResp<Brand> queryBrandListByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化模板
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //根据名称或者根据首字母模糊查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加分页条件
        PageHelper.startPage(page, rows);
        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brandList = this.brandMapper.selectByExample(example);
        //包装成PageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        //包装成分页结果集对象返回
        return new PageResultResp<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    @Transactional
    public void SaveBrand(Brand brand, List<Long> categoryIdList) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", brand.getName());
        int count = brandMapper.selectCountByExample(example);
        if (count != 0) {
            throw new RuntimeException("该品牌已存在,新增失败");
        }
        //先新增商品品牌
        this.brandMapper.insert(brand);
        //批量新增分类品牌中间表
        insertCategoryBrandBatch(brand, categoryIdList);
    }

    @Override
    @Transactional
    public void deleteBrand(Long brandId) {
        //删除中间表
        Example example = new Example(CategoryBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("brandId", brandId);
        categoryBrandMapper.deleteByExample(example);
        //删除品牌
        brandMapper.deleteByPrimaryKey(brandId);
    }

    @Override
    @Transactional
    public void updateBrand(Brand brand, List<Long> categoryIdList) {
        //更新品牌表
        brandMapper.updateByPrimaryKey(brand);
        //更新中间表 先删除后更新
        Example example = new Example(CategoryBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("brandId", brand.getId());
        categoryBrandMapper.deleteByExample(example);
        insertCategoryBrandBatch(brand, categoryIdList);
    }

    /**
     * @MethodName: insertCategoryBrandBatch
     * @Description: //批量新增分类品牌中间表
     * @param brand
     * @param categoryIdList
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/11 18:43
    **/
    private void insertCategoryBrandBatch(Brand brand, List<Long> categoryIdList) {
        List<CategoryBrand> categoryBrandList = new ArrayList<>();
        categoryIdList.forEach(categoryId -> {
            CategoryBrand categoryBrand = new CategoryBrand();
            categoryBrand.setCategoryId(categoryId);
            categoryBrand.setBrandId(brand.getId());
            categoryBrandList.add(categoryBrand);
        });
        categoryBrandMapper.insertList(categoryBrandList);
    }

    @Override
    public Map<Long, String> queryBrandNameMapByIdList(List<Long> brandIdList) {
        List<Brand> brandList = this.brandMapper.selectByIdList(brandIdList);
        if (!CollectionUtils.isEmpty(brandList)) {
            return brandList.stream().collect(Collectors.toMap(Brand::getId, Brand::getName));
        }
        return new HashMap<>();
    }

    @Override
    public List<Brand> queryBrandListByCategoryId(Long categoryId) {
        //先查中间表品牌Id
        Example example = new Example(CategoryBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId", categoryId);
        List<CategoryBrand> categoryBrandList = this.categoryBrandMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(categoryBrandList)) {
            List<Long> brandIdList = categoryBrandList.stream().
                    map(CategoryBrand::getBrandId).collect(Collectors.toList());
            return brandMapper.selectByIdList(brandIdList);
        }
        return null;
    }
}
