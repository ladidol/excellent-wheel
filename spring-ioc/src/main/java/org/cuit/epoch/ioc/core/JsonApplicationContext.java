package org.cuit.epoch.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import org.cuit.epoch.ioc.bean.BeanDefinition;
import org.cuit.epoch.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 16:05
 * @description: {这个容器的作用就是 读取配置文件。将配置文件转换为容器能够理解的 `BeanDefination`。然后使用 `registerBean` 方法。注册这个对象。}
 */
public class JsonApplicationContext extends BeanFactoryImpl {
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });//读配置文件，将配置文件转化为容器能理解的BeanDefination

        System.out.println("将配置文件转化为容器能理解的BeanDefination = " + beanDefinitions);

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {//一一注册这个对象
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}