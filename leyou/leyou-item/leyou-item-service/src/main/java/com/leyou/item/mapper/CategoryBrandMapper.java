package com.leyou.item.mapper;

import com.leyou.item.dao.CategoryBrand;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName CategoryBrandMapper
 * @Description: 乐优分类品牌中间表
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/10 20:30
 * @Version V1.0
 **/
public interface CategoryBrandMapper extends Mapper<CategoryBrand>, MySqlMapper<CategoryBrand> {
}
