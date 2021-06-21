package com.bo.sims.service.impl;

import com.bo.sims.dao.GoodsMapper;
import com.bo.sims.pojo.Goods;
import com.bo.sims.pojo.Sales;
import com.bo.sims.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-19 0:31
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional
    @Override
    public int saveGoods(Goods goods) {
        return goodsMapper.saveGoods(goods);
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        int i;
        try {
            i = goodsMapper.deleteById(id);
        }catch (Exception e){
            return 0;
        }
        return i;
    }


    /**
     * 商品更新操作，如果要更新的商品名存在则更新失败
     * @param goods
     * @return 返回0更新失败，返回1更新成功
     */
    @Transactional
    @Override
    public int updateGoods(Goods goods) {
        int i;
        try {
            i = goodsMapper.updateGoods(goods);
        }catch (Exception e){
            return 0;
        }
        return i;
    }

    @Transactional
    @Override
    public Goods getOneGoodsById(Long id) {
        return goodsMapper.getOneGoodsById(id);
    }

    @Transactional
    @Override
    public Goods getOneGoodsByName(String name) {
        return goodsMapper.getOneGoodsByName(name);
    }

    @Transactional
    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    @Transactional
    @Override
    public List<Goods> getAllGoodsBySupplierId(Long supplierId) {
        return goodsMapper.getAllGoodsBySupplierId(supplierId);
    }


    /**
     * 根据Sales集合中的（商品id，销售量）查询数据库，如果存在该商品且库存量大于或等于销售量时
     * 则更新商品库存量为 库存量-销售量 ，一共对商品表中的salesList.size()个商品进行操作，
     * 只要有其中一个商品出现更新失败或者更新后它的库存量小于0，则进行事务回滚
     * @param salesList
     * @return 如果返回null则证明商品销售量不足或者商品库存量更新失败
     *         如果不为null则是salesList.size()个商品各对应的销售价格
     */
    @Transactional
    @Override
    public List<BigDecimal> getSalePriceAndUpdateCount(List<Sales> salesList) {

        //创建销售价的集合，存放商品的销售价
        List<BigDecimal> salePriceList = new ArrayList<>();

        //查询i个商品的库存量是否大于等于销售量，返回值为null则证明小于销售量，返回值不为null则是该商品的销售价
        for (Sales sales : salesList) {
            BigDecimal salePrice = goodsMapper.getSalePriceByIdAndCount(sales.getGid(), sales.getSalesCount());
            //判断该商品的库存量是否大于等于销售量
            if (salePrice == null) {
                //小于库存量，返回null
                return null;
            }
            //此时库存量已经大于等于销售量，把该商品的销售价放进销售价集合中
            salePriceList.add(salePrice);
        }

        try {
            //更新商品的库存量
            for (Sales sales : salesList) {
                int flag = goodsMapper.updateGoodsCount(sales.getGid(), sales.getSalesCount());
                //判断此次更新是否成功，不成功就抛出异常回滚事务
                if (flag == 0){
                    //手动抛出运行时异常，触发事务回滚功能
                    throw new RuntimeException();
                }
            }
            //查询5个商品更新后的库存量
            for (Sales sales : salesList) {
                Long count = goodsMapper.getCountANDNotLessThanZeroById(sales.getGid());
                //判断更新后此商品的库存量是否大于等于0，不符合就抛出异常回滚事务
                if (count < 0){
                    //手动抛出运行时异常，触发事务回滚功能
                    throw new RuntimeException();
                }
            }
        }catch (Exception e){
            //手动触发事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }

        //将商品的销售价集合返回
        return salePriceList;
    }

    /**
     * 根据Sales集合中的（商品id，销售量）查询数据库中该商品销售销售数量的利润
     * @param salesList
     * @return  返回Sales集合中的商品总利润
     */
    @Transactional
    @Override
    public BigDecimal getProfitsByIdAndSalesCount(List<Sales> salesList) {
        BigDecimal profits = new BigDecimal("0");
        for (Sales sales : salesList) {
            profits = profits.add(goodsMapper.getProfitsByIdAndSalesCount(sales.getGid(),sales.getSalesCount()));
        }
        return profits;
    }
}
