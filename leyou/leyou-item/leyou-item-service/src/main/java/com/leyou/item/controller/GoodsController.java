package com.leyou.item.controller;

import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Sku;
import com.leyou.item.dao.SpuDetail;
import com.leyou.item.dto.SpuDto;
import com.leyou.item.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description: 乐优商品Controller
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/14 22:29
 * @Version V1.0
 **/
@RestController
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @GetMapping("spu/queryGoodsByPage.json")
    public ResponseEntity<PageResultResp<SpuDto>> queryGoodsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows) {
        try {
            PageResultResp<SpuDto> resultResp = this.goodsService.queryGoodsByPage(key, saleable, page, rows);
            if (CollectionUtils.isEmpty(resultResp.getItems())) {
                LOGGER.info("商品列表分页查询结果为空");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(resultResp);
        } catch (Exception e) {
            LOGGER.error("系统异常,商品列表分页查询失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("goods/saveGoods.json")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuDto spuDto) {
        try {
            this.goodsService.saveGoods(spuDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("系统异常,新增商品失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("spu/querySpuDetailBySpuId.json/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId) {
        try {
            SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
            if (spuDetail == null) {
                LOGGER.info("Spu详情查询结果为空");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(spuDetail);
        } catch (Exception e) {
            LOGGER.error("系统异常,Spu详情查询失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("sku/querySkuListBySpuId.json")
    public ResponseEntity<List<Sku>> querySkuListBySpuId(@RequestParam("spuId")Long spuId) {
        try {
            List<Sku> skuList = goodsService.querySkuListBySpuId(spuId);
            if (CollectionUtils.isEmpty(skuList)) {
                LOGGER.info("Sku列表查询结果为空");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(skuList);
        } catch (Exception e) {
            LOGGER.error("系统异常,sku列表查询失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("goods/deleteGoods.json")
    public ResponseEntity<Void> deleteGoods(@RequestParam("spuId")Long spuId) {
        try {
            goodsService.deleteGoods(spuId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,删除商品失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
