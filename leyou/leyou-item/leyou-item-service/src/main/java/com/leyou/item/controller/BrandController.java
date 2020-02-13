package com.leyou.item.controller;

import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Brand;
import com.leyou.item.dao.Category;
import com.leyou.item.dto.BrandDto;
import com.leyou.item.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName BrandController
 * @Description: 乐优品牌Controller
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/8 17:04
 * @Version V1.0
 **/
@RestController
@RequestMapping("brand")
public class BrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @GetMapping("queryBrandListByPage.json")
    public ResponseEntity<PageResultResp<Brand>> queryBrandListByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc) {
        PageResultResp<Brand> resultResp = brandService.queryBrandListByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(resultResp.getItems())) {
            //查询结果为空
            LOGGER.info("查询结果为空");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultResp);
    }

      @PostMapping("saveBrand.json")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandDto brandDto) {
        try {
            //创建品牌对象
            Brand brand = new Brand();
            brand.setName(brandDto.getName());
            brand.setLetter(brandDto.getLetter());
            brand.setImage(brandDto.getImage());
            //String转list
            List<Long> categoryIdList = Arrays.stream(brandDto.getCategoryIds().split(",")).
                    map(Long::parseLong).collect(Collectors.toList());
            this.brandService.SaveBrand(brand, categoryIdList);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("系统异常,新增品牌失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("updateBrand.json")
    public ResponseEntity<Void> updateBrand(@RequestBody BrandDto brandDto) {
        try {
            //处理对象
            Brand brand = new Brand();
            brand.setId(brandDto.getId());
            brand.setName(brandDto.getName());
            brand.setLetter(brandDto.getLetter());
            brand.setImage(brandDto.getImage());
            //将分类Id转换成list
            List<Long> categoryIdList = Arrays.stream(brandDto.getCategoryIds().split(",")).
                    map(Long::parseLong).collect(Collectors.toList());
            //调用Service方法
            this.brandService.updateBrand(brand, categoryIdList);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            LOGGER.error("系统异常,修改品牌失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("deleteBrand.json")
    public ResponseEntity<Void> deleteBrand(@RequestParam("brandId")Long brandId) {
        try {
            this.brandService.deleteBrand(brandId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,删除品牌失败!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };
}
