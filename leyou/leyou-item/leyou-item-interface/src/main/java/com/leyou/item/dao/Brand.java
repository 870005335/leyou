package com.leyou.item.dao;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Brand
 * @Description: 乐优商城品牌实体类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 13:40
 * @Version V1.0
 **/
@Data
@Table(name = "tb_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// 品牌名称
    private String image;// 品牌图片
    private Character letter;
}
