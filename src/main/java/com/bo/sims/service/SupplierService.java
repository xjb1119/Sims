package com.bo.sims.service;

import com.bo.sims.pojo.Supplier;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 17:35
 */
public interface SupplierService {

    int saveSupplier(Supplier supplier);

    int updateSupplier(Supplier supplier);

    int deleteById(Long id);

    Supplier getOneSupplierByName(String name);

    Supplier getOneSupplierById(Long id);

    List<Supplier> getAllSupplier();

}
