package com.leyou.item.dao;

import lombok.Data;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @ClassName SpecGroup
 * @Description: 规格参数分组实体类
 * @Author Arthas_liubin@Foxmail.com
 * @Date 2020/2/13 21:04
 * @Version V1.0
 **/
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params;
}
