package com.bo.sims.controller;

import com.bo.sims.pojo.Goods;
import com.bo.sims.service.GoodsService;
import com.bo.sims.service.SupplierService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Bo
 * @create 2021-06-18 21:50
 */
@Controller
@RequestMapping("/admin")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SupplierService supplierService;

    /**
     * 商品信息展示
     *
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/goods")
    public String goodsList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               Model model) {
        String orderBy = "goodsId desc";
        PageHelper.startPage(pageNum, 5, orderBy);
        List<Goods> list = goodsService.getAllGoods();
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/goods";
    }


    /**
     * 跳转到商品新增页面
     * @return
     */
    @GetMapping("/goods/input")
    public String input(Model model){
        model.addAttribute("goods",new Goods());
        model.addAttribute("suppliers",supplierService.getAllSupplier());
        return "admin/goods-input";
    }


    /**
     * 处理点击修改后跳转到商品新增页面，并把需要修改的商品对象放在model里传给页面回显
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/goods/{id}/input")
    public String update(@PathVariable("id") Long id,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         Model model){
        model.addAttribute("goods",goodsService.getOneGoodsById(id));
        model.addAttribute("suppliers",supplierService.getAllSupplier());
        model.addAttribute("pageNum",pageNum);
        return "admin/goods-input";
    }


    /**
     * goods-input页面的提交功能(插入操作)
     * @param goods
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/goods")
    public String saveGoods(@Valid Goods goods,
                            BindingResult result,
                            RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/goods-input";
        }
        if (goodsService.getOneGoodsByName(goods.getName()) != null){
            result.rejectValue("name","nameError","不能添加重复的商品");
            return "admin/goods-input";
        }
        if (goodsService.saveGoods(goods) == 0){
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/goods";
    }


    /**
     * goods-input页面的提交功能(更新操作)
     * @param goods
     * @param id
     * @param pageNum
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/goods/{id}")
    public String updateSupplier(@Valid Goods goods,
                                 @PathVariable("id") Long id,
                                 @RequestParam("pageNum") Integer pageNum,
                                 BindingResult result,
                                 RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/suppliers-input";
        }
        goods.setId(id);
        if (goodsService.updateGoods(goods) == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/goods?pageNum=" + pageNum;
    }


    /**
     * 商品列表页面的删除功能
     * @param id
     * @param pageNum
     * @param attributes
     * @return
     */
    @GetMapping("/goods/{id}/delete")
    public String deleteId(@PathVariable("id") Long id,
                           @RequestParam("pageNum") Integer pageNum,
                           RedirectAttributes attributes){
        Goods goods = goodsService.getOneGoodsById(id);
        if (goods != null){
            if( goodsService.deleteById(id) != 0){
                attributes.addFlashAttribute("message","删除成功");
            }else {
                //删除商品失败可能的销售表中存放该商品id作为外键
                attributes.addFlashAttribute("message","删除失败，该商品正在销售中");
            }
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/goods?pageNum=" + pageNum;
    }

    /**
     * 商品页面搜索功能
     * @param goodsName
     * @param attributes
     * @param model
     * @return
     */
    @GetMapping("/goods/search")
    public String search(@RequestParam("goodsName") String goodsName,
                         RedirectAttributes attributes,
                         Model model){
        Goods goods = goodsService.getOneGoodsByName(goodsName);
        if (goods == null){
            attributes.addFlashAttribute("message","查找失败");
            return "redirect:/admin/goods";
        }else {
            model.addAttribute("goods",goods);
            model.addAttribute("suppliers",supplierService.getAllSupplier());
            return "admin/goods-input";
        }
    }





}
