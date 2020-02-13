package com.leyou.item.dao;

import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName CategoryBrand
 * @Description: 乐优分类品牌中间表
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/10 20:28
 * @Version V1.0
 **/
@Data
@Table(name = "tb_category_brand")
public class CategoryBrand {

    private Long categoryId;

    private Long brandId;
}
