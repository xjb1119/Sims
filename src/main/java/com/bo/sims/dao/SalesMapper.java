package com.bo.sims.dao;

import com.bo.sims.pojo.Sales;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-19 14:08
 */
@Mapper
@Repository
public interface SalesMapper {


    /**
     * 保存新的销售记录
     * @param sales
     * @return
     */
    @Insert("insert into t_sales (gid,bid,sales_count) values (#{gid},#{bid},#{salesCount})")
    int saveSales(Sales sales);


    /**
     * 根据订单号查询该订单的销售详情
     * @param billId
     * @return
     */
    List<Sales> getSalesBill(Long billId);


}
