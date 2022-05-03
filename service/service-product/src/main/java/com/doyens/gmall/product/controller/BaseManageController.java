package com.doyens.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.common.result.Result;
import com.doyens.gmall.model.product.*;
import com.doyens.gmall.product.mapper.BaseAttrInfoMapper;
import com.doyens.gmall.product.service.ManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/product")
@Api("商品基础基础属性接口")
public class BaseManageController {
    @Autowired
    private ManageService manageService;

    @GetMapping("getCategory1")
    public Result<List<BaseCategory1>> getcategory() {
        List<BaseCategory1> category1 = manageService.getCategory1();
        return Result.ok(category1);
    }

    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getcategory2(@PathVariable("category1Id") long category1Id) {
        List<BaseCategory2> category2 = manageService.getCategory2(category1Id);
        return Result.ok(category2);
    }

    @GetMapping("getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getcategory3(@PathVariable("category2Id") long category2Id) {
        List<BaseCategory3> category3 = manageService.getCategory3(category2Id);
        return Result.ok(category3);
    }

    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(
            @PathVariable("category1Id") Long category1Id,
            @PathVariable("category2Id") Long category2Id,
            @PathVariable("category3Id") Long category3Id
    ) {
        List<BaseAttrInfo> attrInfoList = manageService.getAttrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(attrInfoList);
    }

    //保存平台属性
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        manageService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    //根据属性id查询出属性值
    @GetMapping("getAttrValueList/{attrId}")
    public Result<List<BaseAttrValue>> getAttrValueList(@PathVariable Long attrId) {
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        return Result.ok(attrValueList);
    }



}
