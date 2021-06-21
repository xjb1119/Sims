package com.bo.sims.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Bo
 * @create 2021-06-18 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private Long id;

    @NotBlank(message = "商品的名称不能为空")
    private String name;

    @NotNull(message = "商品的库存量不能为空")
    private Long count;

    @NotNull(message = "商品的进价不能为空")
    private BigDecimal primePrice;

    @NotNull(message = "商品的售价不能为空")
    private BigDecimal salePrice;

    @NotBlank(message = "商品的分类不能为空")
    private String type;

    private Long sid;

    private Supplier supplier;

}
