package com.bo.sims.service;

import com.bo.sims.pojo.Sales;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-20 15:11
 */
public interface SalesService {


    int saveSalesList(List<Sales> salesList);

    List<Sales> getSalesBill(Long billId);
}
