package com.doyens.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.common.result.Result;
import com.doyens.gmall.model.product.BaseTrademark;
import com.doyens.gmall.product.service.BaseTrademarkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("品牌列表展示")
@RestController
@RequestMapping("admin/product/baseTrademark")
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;


    @GetMapping("{page}/{limit}")
    public Result getPage(@PathVariable Long page, @PathVariable Long limit, BaseTrademark baseTrademark) {
        Page<BaseTrademark> BaseTrademarkPage = new Page<>(page, limit);
        IPage<BaseTrademark> spupage = baseTrademarkService.getPage(BaseTrademarkPage, baseTrademark);
        return Result.ok(spupage);
    }

    @PostMapping("save")
    public Result save(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    @PutMapping("update")
    public Result update(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        baseTrademarkService.remove(id);
        return Result.ok();
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    //加载品牌属性
    @GetMapping("getTrademarkList")
    public Result<List<BaseTrademark>> getTrademarkList() {
        List<BaseTrademark> baseTrademarks = baseTrademarkService.list(null);
        return Result.ok(baseTrademarks);
    }


}
