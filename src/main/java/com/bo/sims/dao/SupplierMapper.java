package com.bo.sims.dao;

import com.bo.sims.pojo.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 16:38
 */
@Mapper
@Repository
public interface SupplierMapper {


    /**
     * 插入新的供应商信息
     * @param supplier
     * @return
     */
    int saveSupplier(Supplier supplier);

    /**
     * 按supplier对象中的内容更新供应商的信息
     * @param supplier
     * @return
     */
    int updateSupplier(Supplier supplier);


    /**
     * 根据供应商id删除该供应商信息
     * @param id
     * @return
     */
    @Delete("delete from t_supplier where `id` = #{id}")
    int deleteById(Long id);

    /**
     * 根据供应商id查询一条供应商信息
     * @param id
     * @return
     */
    @Select("select * from t_supplier where `id` = #{id}")
    Supplier getOneSupplierById(Long id);

    /**
     * 根据供应商名字查询一条供应商信息
     * @param name
     * @return
     */
    @Select("select * from t_supplier where `name` = #{name}")
    Supplier getOneSupplierByName(String name);


    /**
     * 查询所有供应商信息
     * @return
     */
    @Select("select * from t_supplier")
    List<Supplier> getAllSupplier();





}
