package com.leyou.item.dto;


import lombok.Data;

/**
 * @ClassName BrandDto
 * @Description: 品牌对象Dto
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/10 21:20
 * @Version V1.0
 **/
@Data
public class BrandDto {

    private Long id;

    private String name;

    private Character letter;

    private String image;

    private String categoryIds;
}
