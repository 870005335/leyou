package com.leyou.item.mapper;

import com.leyou.item.dao.Brand;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;


/**
 * @ClassName BrandMapper
 * @Description: 乐优品牌Mapper
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 13:42
 * @Version V1.0
 **/
public interface BrandMapper extends Mapper<Brand>, SelectByIdListMapper<Brand, Long> {
}
