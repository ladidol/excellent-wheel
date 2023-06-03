package org.cuit.epoch.ioc.utils;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 14:59
 * @description: {只写了一个方法，就是通过 className 这个参数获取对象的 Class。}
 */
public class ClassLoaderUtils {
    public static ClassLoader getDefultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过className获取对象的Class对象
     * @param className
     * @return
     */
    public static Class loadClass(String className) {
        try {
            return getDefultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
