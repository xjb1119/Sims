package com.bo.sims.service.impl;

import com.bo.sims.dao.SupplierMapper;
import com.bo.sims.pojo.Supplier;
import com.bo.sims.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 17:38
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;


    @Transactional
    @Override
    public int saveSupplier(Supplier supplier) {
        return supplierMapper.saveSupplier(supplier);
    }


    /**
     * 供应商更新操作，如果要更新的供应商名存在则更新失败
     * @param supplier
     * @return 返回0更新失败，返回1更新成功
     */
    @Transactional
    @Override
    public int updateSupplier(Supplier supplier) {
        int i;
        try {
            i = supplierMapper.updateSupplier(supplier);
        }catch (Exception e){
            return 0;
        }
        return i;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return supplierMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Supplier getOneSupplierByName(String name) {
        return supplierMapper.getOneSupplierByName(name);
    }

    @Transactional
    @Override
    public Supplier getOneSupplierById(Long id) {
        return supplierMapper.getOneSupplierById(id);
    }

    @Transactional
    @Override
    public List<Supplier> getAllSupplier() {
        return supplierMapper.getAllSupplier();
    }
}
