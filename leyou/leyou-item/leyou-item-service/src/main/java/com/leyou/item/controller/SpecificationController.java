package com.leyou.item.controller;

import com.leyou.item.dao.SpecGroup;
import com.leyou.item.dao.SpecParam;
import com.leyou.item.service.SpecificationService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SpecificationController
 * @Description: 规格参数Controller
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/13 21:28
 * @Version V1.0
 **/
@RestController
@RequestMapping("spec")
public class SpecificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificationController.class);

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("groups/queryGroupsByCategoryId.json/{categoryId}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCategoryId(@PathVariable("categoryId")Long categoryId) {
        try {
            List<SpecGroup> groupList = this.specificationService.queryGroupsByCategoryId(categoryId);
            if (CollectionUtils.isEmpty(groupList)){
                LOGGER.info("未查询到分组信息");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(groupList);
        } catch (Exception e) {
            LOGGER.error("系统异常,查询失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("params/queryParamList.json")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "groupId", required = false)Long groupId,
            @RequestParam(value = "categoryId", required = false)Long categoryId,
            @RequestParam(value = "generic", required = false)Boolean generic,
            @RequestParam(value = "searching", required = false)Boolean searching) {
        try {
            List<SpecParam>  params = this.specificationService.queryParamList(groupId, categoryId, generic, searching);
            if (CollectionUtils.isEmpty(params)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(params);
        } catch (Exception e) {
            LOGGER.error("系统异常,查询失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("groups/saveSpecGroup.json")
    public ResponseEntity<Void> saveSpecGroup(@RequestBody SpecGroup group) {
        try {
            specificationService.saveSpecGroup(group);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,新增规格参数分组失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("groups/updateSpecGroup.json")
    public ResponseEntity<Void> updateSpecGroup(@RequestBody SpecGroup group) {
        try {
            specificationService.updateSpecGroup(group);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("系统异常,编辑规格参数分组失败!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("groups/deleteSpecGroup.json/{groupId}")
    public ResponseEntity<Void> deleteSpecGroup(@PathVariable("groupId")Long groupId) {
        try {
            specificationService.deleteSpecGroup(groupId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,编辑规格参数分组失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("params/saveSpecParam.json")
    public ResponseEntity<Void> saveSpecParam(@RequestBody SpecParam param) {
        try {
            specificationService.saveSpecParam(param);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,新增规格参数失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("prams/updateSpecParam.json")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam param) {
        try {
            specificationService.updateSpecParam(param);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,编辑规格参数失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("params/deleteSpecParam.json/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable("id")Long id) {
        try {
            specificationService.deleteSpecParam(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("系统异常,删除规格参数失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
