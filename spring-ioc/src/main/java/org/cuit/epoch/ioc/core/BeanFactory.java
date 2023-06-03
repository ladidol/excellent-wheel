package org.cuit.epoch.ioc.core;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 15:31
 * @description: {前只支持一种 ByName 的注入。所以我们的 BeanFactory 就只有一个方法：}
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
