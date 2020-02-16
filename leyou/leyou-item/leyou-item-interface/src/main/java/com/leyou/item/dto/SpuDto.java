package com.leyou.item.dto;

import com.leyou.item.dao.Sku;
import com.leyou.item.dao.Spu;
import com.leyou.item.dao.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SpuDto
 * @Description: SpuDto对象
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/14 22:20
 * @Version V1.0
 **/
@Data
public class SpuDto extends Spu {

    String cname;// 商品分类名称

    String bname;// 品牌名称

    SpuDetail spuDetail; //商品详情

    List<Sku> skus; //sku列表
}
