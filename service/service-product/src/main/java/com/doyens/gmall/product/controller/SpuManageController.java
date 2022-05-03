package com.doyens.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.common.result.Result;
import com.doyens.gmall.model.product.BaseSaleAttr;
import com.doyens.gmall.model.product.BaseTrademark;
import com.doyens.gmall.model.product.SpuInfo;
import com.doyens.gmall.model.product.SpuSaleAttr;
import com.doyens.gmall.product.mapper.SpuInfoMapper;
import com.doyens.gmall.product.service.BaseTrademarkService;
import com.doyens.gmall.product.service.ManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@Api(tags = "spu列表展示")
@RestController
@RequestMapping("admin/product")
public class SpuManageController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private BaseTrademarkService baseTrademarkService;

    @GetMapping("{page}/{limit}")
    public Result getSpuInfoPage(@PathVariable Long page, @PathVariable Long limit, SpuInfo spuInfo) {
        Page<SpuInfo> spuInfoPage = new Page<>(page, limit);
        IPage<SpuInfo> spupage = manageService.getSpuInfoPage(spuInfoPage, spuInfo);
        return Result.ok(spupage);

    }

    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList() {
        List<BaseSaleAttr> baseSaleAttrList = manageService.getBaseSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }

    @PostMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        manageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

}
