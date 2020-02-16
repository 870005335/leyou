package com.leyou.item.service;

import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Brand;
import com.leyou.item.dao.Category;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BrandService
 * @Description: 乐优品牌接口
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 17:03
 * @Version V1.0
 **/
public interface BrandService {

    /**
     * @MethodName: queryBrandListByPage
     * @Description: 品牌分页查询
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @Return: com.leyou.common.dao.PageResultResp<com.leyou.item.dao.Brand>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/8 17:15
    **/
    PageResultResp<Brand> queryBrandListByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    /**
     * @MethodName: SaveBrand
     * @Description: 品牌新增
     * @param brand
     * @param categoryIdList
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/10 20:26
    **/
    void SaveBrand(Brand brand, List<Long> categoryIdList);

    /**
     * @MethodName: deleteBrand
     * @Description: 品牌删除
     * @param brandId
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/10 22:03
    **/
    void deleteBrand(Long brandId);

    /**
     * @MethodName: updateBrand
     * @Description: 品牌编辑
     * @param brand
     * @param categoryIdList
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/11 18:36
    **/
    void updateBrand(Brand brand, List<Long> categoryIdList);

    /**
     * @MethodName: queryBrandNameMapByIdList
     * @Description: 根据Id列表查询品牌名称Map
     * @param brandIdList
     * @Return: java.util.Map<java.lang.Long,java.lang.String>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 23:31
    **/
    Map<Long, String> queryBrandNameMapByIdList(List<Long> brandIdList);

    /**
     * @MethodName: queryBrandListByCategoryId
     * @Description: 根据分类Id查询品牌列表
     * @param categoryId
     * @Return: java.util.List<com.leyou.item.dao.Brand>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 11:27
    **/
    List<Brand> queryBrandListByCategoryId(Long categoryId);
}
