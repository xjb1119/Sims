package com.bo.sims.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Bo
 * @create 2021-06-19 13:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Settlement {

    private Long goodId1;
    private Long salesCount1;

    private Long goodId2;
    private Long salesCount2;

    private Long goodId3;
    private Long salesCount3;

    private Long goodId4;
    private Long salesCount4;

    private Long goodId5;
    private Long salesCount5;

    @NotNull(message = "应付金额不能为空")
    private BigDecimal realPayment;
}
