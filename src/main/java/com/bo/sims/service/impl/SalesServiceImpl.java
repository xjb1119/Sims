package com.bo.sims.service.impl;

import com.bo.sims.dao.SalesMapper;
import com.bo.sims.pojo.Sales;
import com.bo.sims.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-20 15:12
 */
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesMapper salesMapper;


    @Transactional
    @Override
    public int saveSalesList(List<Sales> salesList) {
        for (Sales sales : salesList) {
            salesMapper.saveSales(sales);
        }
        return 1;
    }

    @Transactional
    @Override
    public List<Sales> getSalesBill(Long billId) {
        return salesMapper.getSalesBill(billId);
    }
}
