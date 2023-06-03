package org.cuit.epoch.ioc.bean;

import lombok.Data;

import java.util.List;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 14:54
 * @description: {BeanDefinition 是我们项目的核心数据结构。用于描述我们需要 IoC 框架管理的对象。}
 */
@Data
public class BeanDefinition {

    private String name;

    private String className;

    private String interfaceName;

    private List<ConstructorArg> constructorArgs;//构造器参数

    private List<PropertyArg> propertyArgs;//属性


}
