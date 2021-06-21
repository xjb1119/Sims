package com.bo.sims.service;

import com.bo.sims.pojo.Bill;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-20 16:53
 */
public interface BillService {

    int saveBill(Bill bill);

    List<Bill> getProfitsAndTime();
}
