package com.bo.sims.dao;

import com.bo.sims.pojo.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Bo
 * @create 2021-06-19 13:35
 */
@Mapper
@Repository
public interface BillMapper {

    /**
     * 保存新的订单
     * @param bill
     * @return
     */
    int saveBill(Bill bill);

    /**
     * 查询每天的总利润放在bill对象中，返回bill集合
     * @return
     */
    List<Bill> getProfitsAndTime();
}
