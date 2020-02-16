package com.leyou.item.service;

import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Sku;
import com.leyou.item.dao.SpuDetail;
import com.leyou.item.dto.SpuDto;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: 乐优商品接口
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/14 22:28
 * @Version V1.0
 **/
public interface GoodsService {

    /**
     * @MethodName: queryGoodsByPage
     * @Description: 商品列表分页查询
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @Return: com.leyou.common.dao.PageResultResp<com.leyou.item.dto.SpuDto>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 22:39
    **/
    PageResultResp<SpuDto> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows);

    /**
     * @MethodName: saveGoods
     * @Description: 新增商品
     * @param spuDto
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 15:34
    **/
    void saveGoods(SpuDto spuDto);

    /**
     * @MethodName: querySpuDetail
     * @Description: 查询Spu详情
     * @param spuId
     * @Return: com.leyou.item.dao.SpuDetail
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 18:32
    **/
    SpuDetail querySpuDetailBySpuId(Long spuId);

    /**
     * @MethodName: querySkuListBySpuId
     * @Description: 查询Sku列表
     * @param spuId
     * @Return: java.util.List<com.leyou.item.dao.Sku>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 18:56
    **/
    List<Sku> querySkuListBySpuId(Long spuId);

    /**
     * @MethodName: deleteGoods
     * @Description: 删除商品
     * @param spuId
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 21:01
    **/
    void deleteGoods(Long spuId);
}
