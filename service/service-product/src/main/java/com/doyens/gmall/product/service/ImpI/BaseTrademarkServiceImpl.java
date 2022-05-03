package com.doyens.gmall.product.service.ImpI;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doyens.gmall.model.product.BaseTrademark;
import com.doyens.gmall.model.product.SpuInfo;
import com.doyens.gmall.product.mapper.BaseTrademarkMapper;
import com.doyens.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BaseTrademarkService")
public class BaseTrademarkServiceImpl implements BaseTrademarkService {
    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;


    @Override
    public IPage<BaseTrademark> getPage(Page<BaseTrademark> pageParam, BaseTrademark baseTrademark) {
        QueryWrapper<BaseTrademark> queryWrapper = new QueryWrapper<>();
        IPage<BaseTrademark> page = baseTrademarkMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public void save(BaseTrademark baseTrademark) {
        baseTrademarkMapper.insert(baseTrademark);
    }

    @Override
    public void updateById(BaseTrademark baseTrademark) {
        baseTrademarkMapper.updateById(baseTrademark);
    }

    @Override
    public void remove(Long id) {
        baseTrademarkMapper.deleteById(id);
    }

    @Override
    public BaseTrademark getById(String id) {
        BaseTrademark baseTrademark = baseTrademarkMapper.selectById(id);
        return baseTrademark;
    }

    @Override
    public List<BaseTrademark> list(Object o) {
        List<BaseTrademark> baseTrademarks = baseTrademarkMapper.selectList(null);
        return baseTrademarks;
    }


}
