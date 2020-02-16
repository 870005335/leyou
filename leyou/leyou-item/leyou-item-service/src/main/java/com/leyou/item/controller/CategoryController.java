package com.leyou.item.controller;

import com.leyou.item.dao.Category;
import com.leyou.item.dto.BrandDto;
import com.leyou.item.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description: 商品分类Controller
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/7 22:12
 * @Version V1.0
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("queryCategoryListByParentId.json")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(@RequestParam("pid")Long pid) {
        try {
            if (pid == null || pid < 0) {
                LOGGER.info("请求pid不符合要求");
                return ResponseEntity.badRequest().build();
            }
            List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);
            if (CollectionUtils.isEmpty(categoryList)) {
                //查询结果为空
                LOGGER.info("查询结果为空");
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            LOGGER.error("系统异常,根据分类列表失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("queryCategoryByBrandId.json/{brandId}")
    public ResponseEntity<List<Category>> queryCategoryByBrandId(@PathVariable("brandId")Long brandId) {
        try {
            List<Category> categoryList = categoryService.queryCategoryListByBrandId(brandId);
            if (CollectionUtils.isEmpty(categoryList)) {
                LOGGER.info("查询结果为空");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            LOGGER.error("系统异常,根据品牌Id查询分类列表失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
