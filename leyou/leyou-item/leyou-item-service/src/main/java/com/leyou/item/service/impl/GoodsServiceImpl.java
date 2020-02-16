package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.dao.PageResultResp;
import com.leyou.item.dao.Sku;
import com.leyou.item.dao.Spu;
import com.leyou.item.dao.SpuDetail;
import com.leyou.item.dao.Stock;
import com.leyou.item.dto.SpuDto;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName GoodsServiceImpl
 * @Description: 乐优商品接口实现类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/14 22:28
 * @Version V1.0
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private SpuMapper spuMapper;

    @Autowired(required = false)
    private SpuDetailMapper spuDetailMapper;

    @Autowired(required = false)
    private SkuMapper skuMapper;

    @Autowired(required = false)
    private StockMapper stockMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageResultResp<SpuDto> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows) {
        //商品分页查询
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        //开启分页
        PageHelper.startPage(page, rows);
        List<Spu> spuList = spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);
        //取出品牌Id列表查询品牌名称
        List<Long> brandIdList = spuList.stream().map(Spu::getBrandId).distinct().collect(Collectors.toList());
        Map<Long, String> brandNameMap = brandService.queryBrandNameMapByIdList(brandIdList);
        //查询分类名称
        Map<Long, String> categoryNameMap = getCategoryMap(spuList);
        List<SpuDto> resultList = new ArrayList<>();
        spuList.forEach(spu -> {
            SpuDto spuDto = new SpuDto();
            BeanUtils.copyProperties(spu, spuDto);
            if (StringUtils.isNotBlank(brandNameMap.get(spu.getBrandId()))) {
                spuDto.setBname(brandNameMap.get(spu.getBrandId()));
            }
            spuDto.setCname(categoryNameMap.get(spu.getCid1()) + "/" +
                    categoryNameMap.get(spu.getCid2()) + "/" + categoryNameMap.get(spu.getCid3()));
            resultList.add(spuDto);
        });
        return new PageResultResp<>(pageInfo.getTotal(), resultList);
    }

    /**
     * @MethodName: getCategoryMap
     * @Description: 获取分类名称Map
     * @param spuList
     * @Return: java.util.Map<java.lang.Long,java.lang.String>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/14 23:56
    **/
   private Map<Long, String> getCategoryMap(List<Spu> spuList) {
        //处理分类ID
        List<Long> categoryIdList = Stream.of(spuList.stream().map(Spu::getCid1).collect(Collectors.toList()),
               spuList.stream().map(Spu::getCid2).collect(Collectors.toList()),
               spuList.stream().map(Spu::getCid3).collect(Collectors.toList()))
               .flatMap(Collection::stream).distinct().collect(Collectors.toList());
        return this.categoryService.queryCategoryNameMapByIdList(categoryIdList);
   }


    @Override
    @Transactional
    public void saveGoods(SpuDto spuDto) {
        Date nowDate = new Date();
        //spu主键为空 新增操作
        if (spuDto.getId() == null) {
            //新增spu
            spuDto.setSaleable(true);
            spuDto.setValid(true);
            spuDto.setCreateTime(nowDate);
            spuDto.setLastUpdateTime(nowDate);
            this.spuMapper.insertSelective(spuDto);
            //新增SpuDetail
            SpuDetail spuDetail = spuDto.getSpuDetail();
            spuDetail.setSpuId(spuDto.getId());
            this.spuDetailMapper.insertSelective(spuDetail);
        } else {
            // 查询以前sku
            List<Sku> skuList = this.querySkuListBySpuId(spuDto.getId());
            // 如果以前存在，则删除
            if(!CollectionUtils.isEmpty(skuList)) {
                List<Long> skuIdList = skuList.stream().map(Sku::getId).collect(Collectors.toList());
                // 删除以前库存
                Example example = new Example(Stock.class);
                example.createCriteria().andIn("skuId", skuIdList);
                this.stockMapper.deleteByExample(example);
                // 删除以前的sku
                Sku record = new Sku();
                record.setSpuId(spuDto.getId());
                this.skuMapper.delete(record);
                // 更新spu
                spuDto.setLastUpdateTime(nowDate);
                spuDto.setCreateTime(null);
                spuDto.setValid(null);
                spuDto.setSaleable(null);
                this.spuMapper.updateByPrimaryKeySelective(spuDto);

                // 更新spu详情
                this.spuDetailMapper.updateByPrimaryKeySelective(spuDto.getSpuDetail());
            }
        }
        //新增Sku和库存
        saveSkuAndStock(spuDto, nowDate);
    }

    /**
     * @MethodName: saveSkuAndStock
     * @Description: 新增Sku和库存
     * @param spuDto
     * @Return: void
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 16:12
    **/
    private void saveSkuAndStock(SpuDto spuDto, Date nowDate) {
        List<Sku> skuList = spuDto.getSkus();
        skuList.forEach(sku -> {
            // 新增sku
            sku.setSpuId(spuDto.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(nowDate);
            this.skuMapper.insertSelective(sku);

            // 新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        });
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public List<Sku> querySkuListBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            List<Long> skuIdList = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            Map<Long, Integer> stockMap = queryStockMap(skuIdList);
            skuList.forEach(s -> {
                if (stockMap.get(s.getId()) != null) {
                    s.setStock(stockMap.get(s.getId()));
                }
            });
            return skuList;
        }
        return null;
    }

    /**
     * @MethodName: queryStockMap
     * @Description: 查询库存
     * @param skuIdList
     * @Return: java.util.Map<java.lang.Long,java.lang.Integer>
     * @Author: Arthas_liubin@Foxmail.com
     * @Date: 2020/2/15 19:01
    **/
    private Map<Long, Integer> queryStockMap(List<Long> skuIdList) {
        List<Stock> stockList = stockMapper.selectByIdList(skuIdList);
        if (CollectionUtils.isEmpty(stockList)) {
            return new HashMap<>();
        }
        return stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
    }

    @Override
    @Transactional
    public void deleteGoods(Long spuId) {
        //删除spu
        spuMapper.deleteByPrimaryKey(spuId);
        //删除spu详情
        spuDetailMapper.deleteByPrimaryKey(spuId);
        //先查询sku列表
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除sku
            skuMapper.delete(sku);
            //删除stock表
            List<Long> skuIdList = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            Example example = new Example(Stock.class);
            example.createCriteria().andIn("skuId", skuIdList);
            stockMapper.deleteByExample(example);
        }
    }
}
