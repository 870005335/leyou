package com.leyou.item.controller;

import com.leyou.item.dao.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(@RequestParam("pid")Long pid) {
        if (pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);
        if (CollectionUtils.isEmpty(categoryList)) {
            //查询结果为空
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryList);
    }
}
