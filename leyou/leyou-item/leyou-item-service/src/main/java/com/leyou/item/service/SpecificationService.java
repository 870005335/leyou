package com.leyou.item.service;

import com.leyou.item.dao.SpecGroup;
import com.leyou.item.dao.SpecParam;

import java.util.List;

/**
 * @ClassName SpecificationService
 * @Description: 规格参数接口
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/13 21:31
 * @Version V1.0
 **/
public interface SpecificationService {

    /**
     * @MethodName: queryGroupsByCategoryId
     * @Description: 根据分类Id查询规格参数分组信息
     * @param categoryId
     * @Return: java.util.List<com.leyou.item.dao.SpecGroup>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/13 21:43
    **/
    List<SpecGroup> queryGroupsByCategoryId(Long categoryId);

    /**
     * @MethodName: queryParamsByGroupId
     * @Description: 根据分组Id查询规格参数列表
     * @param groupId
     * @Return: java.util.List<com.leyou.item.dao.SpecParam>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/13 22:14
    **/
    List<SpecParam> queryParamList(Long groupId, Long categoryId, Boolean generic, Boolean searching);

    /**
     * @MethodName: saveSpecGroup
     * @Description: 新增规格参数分组
     * @param group
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/13 22:35
    **/
    void saveSpecGroup(SpecGroup group);

    /**
     * @MethodName: updateSpecGroup
     * @Description: 编辑规格参数分组
     * @param group
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/13 23:04
    **/
    void updateSpecGroup(SpecGroup group);

    /**
     * @MethodName: deleteSpecGroup
     * @Description: 删除规格参数分组
     * @param groupId
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/13 23:20
    **/
    void deleteSpecGroup(Long groupId);

    /**
     * @MethodName: saveSpecParam
     * @Description: 保存规格参数
     * @param param
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 11:46
    **/
    void saveSpecParam(SpecParam param);

    /**
     * @MethodName: updateSpecParam
     * @Description: 编辑规格参数
     * @param param
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 16:59
    **/
    void updateSpecParam(SpecParam param);

    /**
     * @MethodName: deleteSpecParam
     * @Description: 删除规格参数
     * @param id
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 20:59
    **/
    void deleteSpecParam(Long id);

}
