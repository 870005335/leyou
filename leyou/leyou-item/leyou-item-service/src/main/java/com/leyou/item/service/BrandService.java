package com.leyou.item.service;

import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Brand;
import com.leyou.item.dao.Category;

import java.util.List;

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
}
