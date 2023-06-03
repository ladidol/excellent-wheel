package org.cuit.epoch.ioc.sth;

import lombok.Data;
import org.cuit.epoch.ioc.utils.ReflectionUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@Data
public class MyClass {
    private String name;

    public void doSomething() {
        // 调用 SomeDependency 的私有方法

    }
}
