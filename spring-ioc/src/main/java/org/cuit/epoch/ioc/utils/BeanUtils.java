package org.cuit.epoch.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 15:01
 * @description: {`BeanUtils` 负责处理对象的实例化，这里我们使用了 cglib 这个工具包}
 */
public class BeanUtils {
    public static <T> T instanceByCglib(Class<T> clz, Constructor ctr, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if (ctr == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(ctr.getParameterTypes(), args);
        }
    }
}
