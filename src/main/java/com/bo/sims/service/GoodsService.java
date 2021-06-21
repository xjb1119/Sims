package com.bo.sims.service;

import com.bo.sims.pojo.Goods;
import com.bo.sims.pojo.Sales;
import com.bo.sims.vo.Settlement;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-19 0:31
 */
public interface GoodsService {

    int saveGoods(Goods goods);

    int deleteById(Long id);

    int updateGoods(Goods goods);

    Goods getOneGoodsById(Long id);

    Goods getOneGoodsByName(String name);

    List<Goods> getAllGoods();

    List<Goods> getAllGoodsBySupplierId(Long supplierId);

    List<BigDecimal> getSalePriceAndUpdateCount(List<Sales> salesList);

    BigDecimal getProfitsByIdAndSalesCount(List<Sales> salesList);

}
