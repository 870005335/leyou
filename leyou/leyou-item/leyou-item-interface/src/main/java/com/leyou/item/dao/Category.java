package com.leyou.item.dao;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Category
 * @Description: 商品分类实体类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:00
 * @Version V1.0
 **/
@Data
@Table(name="tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent; // 注意isParent生成的getter和setter方法需要手动加上Is
    private Integer sort;
}
