package com.bo.sims.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bo
 * @create 2021-06-18 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private Long id;

    private BigDecimal needPayment;   //应付金额

    @NotNull(message = "实付金额不能为空")
    private BigDecimal realPayment;   //实付金额

    private BigDecimal profits;       //利润

    private Date createTime;

}
