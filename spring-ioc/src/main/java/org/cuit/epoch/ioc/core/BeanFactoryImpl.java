package org.cuit.epoch.ioc.core;

import org.cuit.epoch.ioc.bean.BeanDefinition;
import org.cuit.epoch.ioc.bean.ConstructorArg;
import org.cuit.epoch.ioc.utils.BeanUtils;
import org.cuit.epoch.ioc.utils.ClassLoaderUtils;
import org.cuit.epoch.ioc.utils.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 15:31
 * @description: {实现了这个方法}
 */
public class BeanFactoryImpl implements BeanFactory {


    //用来存对象名称和对象对应的BeanDefinition数据结构
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefineMap = new ConcurrentHashMap<>();
    //用来保存beanName和实例化后的对象
    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();
    //beanNam的集合
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());

    /**
     * 获取实例，并注入参数
     * 先从容器中获取，如果有，直接返回；
     * 如果没有，就实例化，并注入参数。
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        //查找对象是否已经实例化过
        Object bean = beanMap.get(name);
        if (bean != null) {
            return bean;
        }
        //如果没有实例化，那就需要调用createBean来创建对象
        bean = createBean(beanDefineMap.get(name));

        if (bean != null) {
            //对象创建成功以后，注入对象需要的参数
            populatebean(bean);
            //再把对象存入Map中方便下次使用。
            beanMap.put(name, bean);
        }

        //结束返回
        return bean;
    }

    /**
     * 容器初始化的时候，会调用。
     *
     * @param name
     * @param bd
     */
    protected void registerBean(String name, BeanDefinition bd) {
        beanDefineMap.put(name, bd);
        beanNameSet.add(name);
    }

    /**
     * 通过cglib来对象实例化
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassLoaderUtils.loadClass(beanName);
        if (clz == null) {
            throw new Exception("can not find bean by beanName：" + beanName);
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            System.out.println("构造函数的传参不为空");
            List<Object> objects = new ArrayList<>();
            // TODO: 2023/6/3 为啥这里通过getBean方法的到的引用不会出现异常
            // TODO: 2023/6/3 这里目前没有通过构造函数传参 
            for (ConstructorArg constructorArg : constructorArgs) {
                System.out.println("constructorArg = " + constructorArg.getName());
                objects.add(getBean(constructorArg.getRef()));
            }
            return BeanUtils.instanceByCglib(clz, clz.getConstructor(), objects.toArray());
        } else {
            System.out.println("构造函数的传参为空，我们直接创建");
            return BeanUtils.instanceByCglib(clz, null, null);
        }
    }

    /**
     * 注入参数
     * @param bean
     * @throws Exception
     */
    private void populatebean(Object bean) throws Exception {

        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();

        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);//取消字符串首字母大写，这里统一用首字母小写的驼峰命名。
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field, bean, fieldBean);
                    }
                }
            }
        }
    }
}