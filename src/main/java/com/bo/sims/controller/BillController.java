package com.bo.sims.controller;


import com.bo.sims.pojo.Bill;
import com.bo.sims.pojo.Goods;
import com.bo.sims.pojo.Sales;
import com.bo.sims.service.BillService;
import com.bo.sims.service.GoodsService;
import com.bo.sims.service.SalesService;
import com.bo.sims.vo.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 0:46
 */
@Controller
@RequestMapping("/admin")
public class BillController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BillService billService;

    @Autowired
    private SalesService salesService;


    /**
     * 去订单结算页面
     *
     * @param model
     * @return
     */
    @GetMapping("/bill/payment")
    public String billPayment(Model model) {
        List<Goods> goodsList = goodsService.getAllGoods();
        model.addAttribute("goodsList", goodsList);
        return "admin/bill-payment";
    }


    /**
     * 处理订单结算页面提交的数据，重定向到订单详情页面
     *
     * @param settlement
     * @param attributes
     * @return
     */
    @PostMapping("/bill/settlement")
    public String billSettlement(@Valid Settlement settlement,
                                 RedirectAttributes attributes) {
        //将settlement对象转成Sales的集合（存放商品id和销售量）
        List<Sales> salesList = new ArrayList<>();
        if (settlement.getGoodId1() != null) {
            salesList.add(new Sales(settlement.getGoodId1(), settlement.getSalesCount1()));
        }
        if (settlement.getGoodId2() != null) {
            salesList.add(new Sales(settlement.getGoodId2(), settlement.getSalesCount2()));
        }
        if (settlement.getGoodId3() != null) {
            salesList.add(new Sales(settlement.getGoodId3(), settlement.getSalesCount3()));
        }
        if (settlement.getGoodId4() != null) {
            salesList.add(new Sales(settlement.getGoodId4(), settlement.getSalesCount4()));
        }
        if (settlement.getGoodId5() != null) {
            salesList.add(new Sales(settlement.getGoodId5(), settlement.getSalesCount5()));
        }
        //如果salesList集合为空则证明没有商品需要结算，结算失败
        if (salesList.size() == 0) {
            attributes.addFlashAttribute("message", "结算失败");
            return "redirect:/admin/bill/payment";
        }
        List<BigDecimal> salePriceList = goodsService.getSalePriceAndUpdateCount(salesList);
        if (salePriceList == null) {
            //结算失败，service层已经进行事务回滚
            attributes.addFlashAttribute("message", "结算失败");
            return "redirect:/admin/bill/payment";
        }
        //到这里证明结算成功，商品的库存量已更新，且更新后库存量大于或者等0

        //根据salePriceList集合中的每个商品销售价乘上它的销售量，得到i个商品总共的销售总额needPayment
        BigDecimal needPayment = new BigDecimal("0");
        for (int i = 0; i < salePriceList.size(); i++) {
            needPayment = needPayment.add(salePriceList.get(i).multiply(new BigDecimal(salesList.get(i).getSalesCount() + "")));
        }

        //根据salesList集合查询数据库，获得这些商品的总利润
        BigDecimal profits = goodsService.getProfitsByIdAndSalesCount(salesList);

        //创建订单类的对象，通过构造器给对象赋值，再保存到数据库中
        Bill bill = new Bill(null, needPayment, settlement.getRealPayment(), profits, new Date());
        billService.saveBill(bill);

        //把salesList集合中的Sales对象的订单号(bid)设置为刚插入的订单号
        for (Sales sales : salesList) {
            sales.setBid(bill.getId());
        }

        //保存i条销售记录到数据库
        salesService.saveSalesList(salesList);
        //重定向去订单详细页面
        return "redirect:/admin/bill/details?id=" + bill.getId();
    }

    /**
     * 根据订单id跳转到该订单的详细信息页面
     * 可以处理订单结算页面提交过来的数据，也可以实现搜索栏输入订单号后进行搜索功能
     * 还可以处理点击订单结果的处理
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bill/details")
    public String billDetails(@RequestParam("id") Long id,
                              RedirectAttributes attributes,
                              Model model) {
        if (id == -1) {
            attributes.addFlashAttribute("message", "请先输入商品信息再结算");
            return "redirect:/admin/bill/payment";
        }
        if (salesService.getSalesBill(id).size() == 0) {
            attributes.addFlashAttribute("message", "查找失败，订单号不存在");
            return "redirect:/admin/bill/payment";
        }
        //根据订单号查询该订单的5条销售记录下的详细订单信息
        List<Sales> billDetails = salesService.getSalesBill(id);
        //放在mode给前端页面显示
        model.addAttribute("billDetails", billDetails);
        model.addAttribute("billId", billDetails.get(0).getBid());
        return "admin/bill-details";
    }


    /**
     * 去账目日结页面
     *
     * @param model
     * @return
     */
    @GetMapping("/dailyAccounts")
    public String dailyAccounts(Model model) {
        List<Bill> profitsAndTime = billService.getProfitsAndTime();
        model.addAttribute("profitsAndTimes", profitsAndTime);
        model.addAttribute("dayCount", profitsAndTime.size());
        return "admin/daily-accounts";
    }

}
