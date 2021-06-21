package com.bo.sims;

import com.bo.sims.dao.GoodsMapper;
import com.bo.sims.dao.SalesMapper;
import com.bo.sims.pojo.Bill;
import com.bo.sims.pojo.Goods;
import com.bo.sims.pojo.Sales;
import com.bo.sims.pojo.Supplier;
import com.bo.sims.service.BillService;
import com.bo.sims.service.GoodsService;
import com.bo.sims.service.SupplierService;
import com.bo.sims.vo.Settlement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 11:47
 */
@SpringBootTest
public class test {

    @Autowired
    SupplierService supplierService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SalesMapper salesMapper;

    @Autowired
    BillService billService;

    @Autowired
    DataSource dataSource;

    @Test
    void test1(){
//        Supplier sup1 = new Supplier(1L, "1号有限公司", "肇庆", 14725813697L);
//        Supplier sup2 = new Supplier(2L, "2号有限公司", "广州", 17554413697L);
//        Supplier sup3 = new Supplier(3L, "3号有限公司", "佛山", 19872581367L);
//        supplierService.saveSupplier(sup1);
//        supplierService.saveSupplier(sup2);
//        supplierService.saveSupplier(sup3);
//        supplierService.deleteById(2L);
//        Supplier sup4 = new Supplier(3L, "4号有限公司", "湛江", 13692581367L);
//        supplierService.updateSupplier(sup4);
        System.out.println(supplierService.getOneSupplierByName("4号有限公司"));
        System.out.println();
        List<Supplier> list = supplierService.getAllSupplier();
        for (Supplier supplier : list) {
            System.out.println(supplier);
        }
    }


    @Test
    void test2(){
        List<Goods> list = goodsService.getAllGoods();
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }

    @Test
    void test3(){
        Goods goods1 = goodsService.getOneGoodsById(1L);
        Goods goods2 = goodsService.getOneGoodsByName("辣条");

        System.out.println(goods1);
        System.out.println(goods2);
    }


    @Test
    void test4(){
//        Goods goods1 = new Goods(1L, "面包", 40L, new BigDecimal(12.4), new BigDecimal(16.5), "食物", 7L, null);
        Goods goods2 = new Goods(2L, "牛奶", 980L, new BigDecimal(2.4), new BigDecimal(5.5), "饮料", 9L, null);
        goodsService.saveGoods(goods2);
    }

    @Test
    void test5(){
        List<Goods> list = goodsService.getAllGoodsBySupplierId(10L);
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }

    @Test
    void test6(){
        List<Sales> list = salesMapper.getSalesBill(1L);
        for (Sales sales : list) {
            System.out.println(sales);
        }
        System.out.println(list.size());
    }


    @Test
    void test7(){
        BigDecimal price = goodsMapper.getSalePriceByIdAndCount(10L, 200L);
        System.out.println(price);
    }


    @Test
    void test8(){
        Settlement settlement = new Settlement(2L, 10L, 2L, 10L, 2L, 10L, 2L, 10L, 2L, 10L, new BigDecimal("1000"));
//        List<BigDecimal> list = goodsService.getSalePriceAndUpdateCount(settlement);
//        System.out.println(list);
        List<Sales> salesList = new ArrayList<>();
        if (settlement.getGoodId1() != null){
            salesList.add(new Sales(settlement.getGoodId1(),settlement.getSalesCount1()));
        }
        if (settlement.getGoodId2() != null){
            salesList.add(new Sales(settlement.getGoodId2(),settlement.getSalesCount2()));
        }
        if (settlement.getGoodId3() != null){
            salesList.add(new Sales(settlement.getGoodId3(),settlement.getSalesCount3()));
        }
        if (settlement.getGoodId4() != null){
            salesList.add(new Sales(settlement.getGoodId4(),settlement.getSalesCount4()));
        }
        if (settlement.getGoodId5() != null){
            salesList.add(new Sales(settlement.getGoodId5(),settlement.getSalesCount5()));
        }
        List<BigDecimal> list = goodsService.getSalePriceAndUpdateCount(salesList);

        System.out.println(list);
    }




    @Test
    void text9(){
        System.out.println(dataSource.getClass());
    }
}
