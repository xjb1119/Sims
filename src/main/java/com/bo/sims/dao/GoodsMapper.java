package com.bo.sims.dao;

import com.bo.sims.pojo.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 13:25
 */
@Mapper
@Repository
public interface GoodsMapper {

    @Insert("insert into t_goods (name,count,prime_price,sale_price,type,sid) values (#{name},#{count},#{primePrice},#{salePrice},#{type},#{sid})")
    int saveGoods(Goods goods);

    @Delete("delete from t_goods where id = #{id}")
    int deleteById(Long id);

    @Update("update t_goods set name = #{name}, count = #{count}, prime_price = #{primePrice}, sale_price = #{salePrice}, type = #{type}, sid = #{sid} where id = #{id}")
    int updateGoods(Goods goods);

    /**
     * 根据商品id查询一条商品信息（带对应的供应商信息）
     *
     * @return
     */
    Goods getOneGoodsById(Long id);

    /**
     * 根据商品名称查询一条商品信息（带对应的供应商信息）
     *
     * @return
     */
    Goods getOneGoodsByName(String name);

    /**
     * 查询所有商品信息（带对应的供应商信息）
     *
     * @return
     */
    List<Goods> getAllGoods();

    /**
     * 根据供应商id查询它旗下所有商品信息（带对应的供应商信息）
     *
     * @param supplierId
     * @return
     */
    List<Goods> getAllGoodsBySupplierId(Long supplierId);


    /**
     * 根据商品id和库存量查询id相等且库存量大于或等于count的商品的售货价
     * 如果当前商品库存量不足count则返回0.0
     * 否则返回该商品的售货价
     *
     * @param id
     * @param count
     * @return
     */
    BigDecimal getSalePriceByIdAndCount(Long id, Long count);


    /**
     * 根据商品id修改该商品的库存量为 原库存量 - （本次销售量）salesCount
     *
     * @param id
     * @param salesCount
     * @return
     */
    int updateGoodsCount(Long id, Long salesCount);


    /**
     * 根据商品id查询它对应的库存量(要求是大于等于0的)
     *
     * @param id
     * @return
     */
    Long getCountANDNotLessThanZeroById(Long id);

    /**
     * 根据商品id和销售量查询出它的总利润 = (售货价 - 进货价) * 销售数量
     *
     * @param id
     * @return
     */
    BigDecimal getProfitsByIdAndSalesCount(Long id, Long salesCount);

}
