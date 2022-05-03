package com.doyens.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.model.product.BaseTrademark;

import java.util.List;

public interface BaseTrademarkService {


    IPage<BaseTrademark> getPage(Page<BaseTrademark> pageParam, BaseTrademark baseTrademark);

    void save(BaseTrademark baseTrademark);


    void updateById(BaseTrademark baseTrademark);

    void remove(Long id);

    BaseTrademark getById(String id);


    List<BaseTrademark> list(Object o);
}
