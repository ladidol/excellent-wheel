package org.cuit.epoch.ioc.test;

import org.cuit.epoch.ioc.core.JsonApplicationContext;
import org.cuit.epoch.ioc.test.entity.Robot;

/**
 * @author: Xiaoqiang-Ladidol
 * @date: 2023/6/3 16:11
 * @description: {}
 */
public class IocTest {

    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot) applicationContext.getBean("robot");
        aiRobot.show();
    }


}
