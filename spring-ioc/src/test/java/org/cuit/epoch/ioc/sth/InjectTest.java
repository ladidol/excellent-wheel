package org.cuit.epoch.ioc.sth;

import org.cuit.epoch.ioc.utils.ReflectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
public class InjectTest {
    @Test
    public void testInjectField() throws IllegalAccessException, NoSuchFieldException {
        // 创建一个需要测试的对象
        MyClass obj = new MyClass();

        // 使用 ReflectionUtils.injectField() 方法，将 obj 的私有字段 value 的值设置为 42
        Field field = obj.getClass().getDeclaredField("name");
        ReflectionUtils.injectField(field, obj, "ladidol");

        // 验证 obj 的 value 字段是否被成功修改
        System.out.println("name = " + obj.getName());
        Assert.assertEquals("ladidol", obj.getName());
    }
}