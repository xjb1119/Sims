package com.bo.sims.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Bo
 * @create 2021-06-18 10:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    private Long id;

    //用@NotBlank注解标识name不能为空
    @NotBlank(message = "供应商的名称不能为空")
    private String name;

    @NotBlank(message = "供应商的地址不能为空")
    private String address;

    @NotNull(message = "供应商的联系方式不能为空")
    private Long contact;

}
