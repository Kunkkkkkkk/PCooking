package com.ruoyi.common.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息对象 my_message
 * 
 * @author ruoyi
 */
@Getter
@Setter
public class MyMessage 
{
    /** 消息ID */
    private String id;

    /** 消息内容 */
    private String message;
} 