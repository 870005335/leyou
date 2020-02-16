package com.leyou.item.dao;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Stock
 * @Description: 库存实体类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/15 15:01
 * @Version V1.0
 **/
@Data
@Table(name = "tb_stock")
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock;// 秒杀可用库存
    private Integer seckillTotal;// 已秒杀数量
    private Integer stock;// 正常库存
}