package com.leyou.item.service.impl;

import com.leyou.item.dao.SpecGroup;
import com.leyou.item.dao.SpecParam;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: 规格参数接口实现类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/13 21:31
 * @Version V1.0
 **/
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired(required = false)
    private SpecGroupMapper specGroupMapper;

    @Autowired(required = false)
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupsByCategoryId(Long categoryId) {
        SpecGroup group = new SpecGroup();
        group.setCid(categoryId);
        return specGroupMapper.select(group);
    }

    @Override
    public List<SpecParam> queryParamList(Long groupId, Long categoryId, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(groupId);
        record.setCid(categoryId);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }

    @Override
    @Transactional
    public void saveSpecGroup(SpecGroup group) {
        //保证同一分类下没有重复的分组
        Example example = new Example(SpecGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid", group.getCid()).andEqualTo("name", group.getName());
        int count = specGroupMapper.selectCountByExample(example);
        if (count == 0) {
            specGroupMapper.insert(group);
        }
    }

    @Override
    @Transactional
    public void updateSpecGroup(SpecGroup group) {
        //保证同一分类下没有重复的分组
        Example example = new Example(SpecGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid", group.getCid()).
                andEqualTo("name", group.getName()).
                andNotEqualTo("id", group.getId());
        int count = specGroupMapper.selectCountByExample(example);
        if (count == 0) {
            specGroupMapper.updateByPrimaryKey(group);
        }
    }

    @Override
    @Transactional
    public void deleteSpecGroup(Long groupId) {
        //先删除分组下的规格参数
        SpecGroup group = specGroupMapper.selectByPrimaryKey(groupId);
        Long categoryId = group.getCid();
        Example example = new Example(SpecParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid", categoryId).andEqualTo("groupId", groupId);
        specParamMapper.deleteByExample(example);
        //删除分组
        specGroupMapper.deleteByPrimaryKey(groupId);
    }

    @Override
    @Transactional
    public void saveSpecParam(SpecParam param) {
        //保证同一分类 同一分组下没有重复规格
        Example example = new Example(SpecParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid", param.getCid()).
                andEqualTo("groupId", param.getGroupId()).
                    andEqualTo("name", param.getName());
        int count = specParamMapper.selectCountByExample(example);
        if (count == 0) {
            specParamMapper.insert(param);
        }
    }

    @Override
    @Transactional
    public void updateSpecParam(SpecParam param) {
        //保证同一分类 同一分组下没有重复规格
        Example example = new Example(SpecParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid", param.getCid()).
                andEqualTo("groupId", param.getGroupId()).
                andEqualTo("name", param.getName()).
                andNotEqualTo("id", param.getId());
        int count = specParamMapper.selectCountByExample(example);
        if (count == 0) {
            specParamMapper.updateByPrimaryKey(param);
        }
    }

    @Override
    @Transactional
    public void deleteSpecParam(Long id) {
        specParamMapper.deleteByPrimaryKey(id);
    }
}
