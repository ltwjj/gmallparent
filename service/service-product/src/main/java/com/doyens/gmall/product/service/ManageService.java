package com.doyens.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.model.product.*;

import java.util.List;

public interface ManageService {

    List<BaseCategory1> getCategory1();

    List<BaseCategory2> getCategory2(Long Category1Id);

    List<BaseCategory3> getCategory3(Long Category2Id);

    List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    //保存平台属性
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    BaseAttrInfo getBaseAttrInfo(Long attrId);

    List<BaseAttrValue> getAttrValueList(Long attrId);


    IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> spuInfoPage, SpuInfo spuInfo);

    //获取销售属性
    List<BaseSaleAttr> getBaseSaleAttrList();

    //spu添加
    void saveSpuInfo(SpuInfo spuInfo);


}