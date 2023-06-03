package org.cuit.epoch.ioc.utils;

import java.lang.reflect.Field;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 15:06
 * @description: {`ReflectionUtils` 主要通过 Java 的反射原理来完成对象的依赖注入：
 * 这段代码的使用场景一般是在需要动态地修改对象的私有字段值时。}
 */
public class ReflectionUtils {

    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}