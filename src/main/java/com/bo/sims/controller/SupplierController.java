package com.bo.sims.controller;

import com.bo.sims.pojo.Goods;
import com.bo.sims.pojo.Supplier;
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


import java.util.List;
import javax.validation.Valid;

/**
 * @author Bo
 * @create 2021-06-18 13:32
 */
@Controller
@RequestMapping("/admin")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;


    @Autowired
    private GoodsService goodsService;

    /**
     * 供应商信息展示
     *
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/supplier")
    public String supplierList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               Model model) {
        //按照排序id 升叙 排序
        String orderBy = "id desc";
        //startPage的三个参数分别为当前页码、每页条数、排列顺序
        PageHelper.startPage(pageNum, 5, orderBy);
        //查询所有数据并封装成集合
        List<Supplier> list = supplierService.getAllSupplier();
        //再通过封装成当页的数据构成的集合
        PageInfo<Supplier> pageInfo = new PageInfo<>(list);
        //把当页的数据放在model传递给页面，在博客管理页面显示数据
        model.addAttribute("pageInfo", pageInfo);

        return "admin/suppliers";
    }


    /**
     * 跳转到供应商新增页面
     *
     * @return
     */
    @GetMapping("/supplier/input")
    public String input(Model model) {
        model.addAttribute("supplier",new Supplier());
        return "admin/suppliers-input";
    }


    /**
     * 处理点击修改后跳转到供应商新增页面，并把需要修改的供应商对象放在model里传给页面回显
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/supplier/{id}/input")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("pageNum") Integer pageNum,
                         Model model) {
        model.addAttribute("supplier", supplierService.getOneSupplierById(id));
        model.addAttribute("pageNum",pageNum);
        return "admin/suppliers-input";
    }


    /**
     * supplier-input页面的提交功能(插入操作)
     * @param supplier
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/supplier")
    public String saveSupplier(@Valid Supplier supplier,
                               BindingResult result,
                               RedirectAttributes attributes) {
        // 后端非空的验证
        // 判断供应商名称、地址、联系方式是否为空，为空则回到供应商新增页面并提示错误信息。
        if (result.hasErrors()) {
            return "admin/suppliers-input";
        }
        // 查询一下新增的供应商名字是否已经存在
        if (supplierService.getOneSupplierByName(supplier.getName()) != null) {
            result.rejectValue("name","nameError","不能添加重复的供应商");
            return "admin/suppliers-input";
        }
        // 新增供应商，并通过返回值验证操作结果
        if (supplierService.saveSupplier(supplier) == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/supplier";
    }


    /**
     * supplier-input页面的提交功能(更新操作)
     * @param supplier
     * @param id
     * @param pageNum
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/supplier/{id}")
    public String updateSupplier(@Valid Supplier supplier,
                               @PathVariable("id") Long id,
                               @RequestParam("pageNum") Integer pageNum,
                               BindingResult result,
                               RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/suppliers-input";
        }
        supplier.setId(id);
        if (supplierService.updateSupplier(supplier) == 0) {
            attributes.addFlashAttribute("message1", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/supplier?pageNum=" + pageNum;
    }


    /**
     * 供应商列表页面的删除功能
     * @param id
     * @param pageNum
     * @param attributes
     * @return
     */
    @GetMapping("/supplier/{id}/delete")
    public String deleteId(@PathVariable("id") Long id,
                           @RequestParam("pageNum") Integer pageNum,
                           RedirectAttributes attributes){
        Supplier supplier = supplierService.getOneSupplierById(id);
        //根据供应商id查询是否还存在需要此供应商供应的商品，如果存在则删除供应商失败
        List<Goods> goodsList = goodsService.getAllGoodsBySupplierId(id);
        if (supplier != null && goodsList.isEmpty()){
            if( supplierService.deleteById(id) != 0){
                attributes.addFlashAttribute("message","删除成功");
            }else {
                attributes.addFlashAttribute("message","删除失败");
            }
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/supplier?pageNum=" + pageNum;
    }

    /**
     * 供应商页面搜索功能
     * @param supplierName
     * @param attributes
     * @param model
     * @return
     */
    @GetMapping("/supplier/search")
    public String search(@RequestParam("supplierName") String supplierName,
                         RedirectAttributes attributes,
                         Model model){
        Supplier supplier = supplierService.getOneSupplierByName(supplierName);
        if (supplier == null){
            attributes.addFlashAttribute("message","查找失败");
            return "redirect:/admin/supplier";
        }else {
            model.addAttribute("supplier",supplier);
            return "admin/suppliers-input";
        }
    }



}
