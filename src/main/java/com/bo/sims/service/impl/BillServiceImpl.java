package com.bo.sims.service.impl;

import com.bo.sims.dao.BillMapper;
import com.bo.sims.pojo.Bill;
import com.bo.sims.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author Bo
 * @create 2021-06-20 16:53
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;


    @Transactional
    @Override
    public int saveBill(Bill bill) {
        return billMapper.saveBill(bill);
    }

    @Transactional
    @Override
    public List<Bill> getProfitsAndTime() {
        return billMapper.getProfitsAndTime();
    }
}
