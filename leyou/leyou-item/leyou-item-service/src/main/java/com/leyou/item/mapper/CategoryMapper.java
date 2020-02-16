package com.leyou.item.mapper;

import com.leyou.item.dao.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName CategoryMapper
 * @Description: 商品分类Mapper
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:24
 * @Version V1.0
 **/
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {
}
