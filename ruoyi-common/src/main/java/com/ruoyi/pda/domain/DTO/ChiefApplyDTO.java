package com.ruoyi.pda.domain.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 厨师申请数据传输对象
 */
@Data
public class ChiefApplyDTO {

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotBlank(message = "身份证号不能为空")
    // @Pattern(regexp = "^\\d{17}(\\d|X)$", message = "身份证号格式不正确") // 可选：添加身份证格式校验
    private String idCode;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话号码格式不正确")
    private String phone;

    @NotBlank(message = "性别不能为空")
    private String sex; // 0:男, 1:女

    @NotNull(message = "从业年限不能为空")
    private Integer years;

    @NotBlank(message = "擅长菜系不能为空")
    private String cuisine; // 逗号分隔的字符串

    @NotBlank(message = "厨师资格证图片URL不能为空")
    private String image;

    @NotBlank(message = "个人介绍不能为空")
    @Size(max = 500, message = "个人介绍不能超过500字")
    private String remark;

    private String realPhoto;
} 