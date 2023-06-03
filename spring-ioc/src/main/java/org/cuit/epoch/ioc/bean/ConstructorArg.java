package org.cuit.epoch.ioc.bean;

import lombok.Data;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 14:57
 * @description: {}
 */
@Data
public class ConstructorArg {

    private int index;
    private String ref;
    private String name;
    private Object value;
}
