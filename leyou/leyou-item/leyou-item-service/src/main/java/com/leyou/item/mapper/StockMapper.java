package com.leyou.item.mapper;

import com.leyou.item.dao.Stock;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName StockMapper
 * @Description: 库存Mapper接口
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/15 16:48
 * @Version V1.0
 **/
public interface StockMapper extends Mapper<Stock>, SelectByIdListMapper<Stock, Long> {
}
