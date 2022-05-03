package com.doyens.gmall.product.service.ImpI;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.model.product.*;
import com.doyens.gmall.product.mapper.*;
import com.doyens.gmall.product.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.nodes.CollectionNode;

import java.util.List;

@Service("ManageService")
public class ManageServiceImpI implements ManageService {
    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;
    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;
    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
   @Autowired
   private BaseSaleAttrMapper baseSaleAttrMapper;
   @Autowired
   private SpuImageMapper spuImageMapper;
   @Autowired
   private SpuSaleAttrMapper spuSaleAttrMapper;
   @Autowired
   private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Override
    public List<BaseCategory1> getCategory1() {
        return baseCategory1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2(Long Category1Id) {
        return baseCategory2Mapper.selectList(new QueryWrapper<BaseCategory2>()
                .eq("category1_id", Category1Id)
        );
    }

    @Override
    public List<BaseCategory3> getCategory3(Long Category2Id) {
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>()
                .eq("category2_id", Category2Id)
        );
    }

    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //判断一下是否有id进来
        if (baseAttrInfo.getId() != null) {
            baseAttrInfoMapper.updateById(baseAttrInfo);
            baseAttrValueMapper.delete(new QueryWrapper<BaseAttrValue>()
                    .eq("Attr_Id", baseAttrInfo.getId())
            );
        } else {
            baseAttrInfoMapper.insert(baseAttrInfo);
        }
        // 获取一下页面传递过来的所有平台属性值的数据
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue : attrValueList) {
            //获取平台属性的id给平台属性值
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insert(baseAttrValue);
        }
    }


    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.selectList(new QueryWrapper<BaseAttrValue>()
                .eq("attr_id", attrId)
        );

        return baseAttrValues;
    }

    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> spuInfoPage, SpuInfo spuInfo) {
        return spuInfoMapper.selectPage(spuInfoPage, new QueryWrapper<SpuInfo>()
                .eq("category3_id", spuInfo.getCategory3Id())
                .orderByDesc("id")
        );
    }




    //根据属性id查询出属性值
    @Override
    public BaseAttrInfo getBaseAttrInfo(Long attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        if (baseAttrInfo != null) {
            List<BaseAttrValue> attrValueList = getAttrValueList(attrId);
            if (!CollectionUtils.isEmpty(attrValueList)) {
                baseAttrInfo.setAttrValueList(attrValueList);
            }
        }
        return baseAttrInfo;
    }

    //获取销售属性
    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        List<BaseSaleAttr> baseSaleAttrs = baseSaleAttrMapper.selectList(null);
        return baseSaleAttrs;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);

        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (!CollectionUtils.isEmpty(spuImageList)){
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insert(spuImage);
            }
        }
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (!CollectionUtils.isEmpty(spuSaleAttrList)){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insert(spuSaleAttr);

                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (!CollectionUtils.isEmpty(spuSaleAttrValueList)){
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }
            }
        }
    }


}
