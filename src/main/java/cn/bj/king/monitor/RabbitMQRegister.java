package cn.bj.king.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Service;

/**
 * Create by 刘海龙 2019年12月30日
 * Copyright (c) 2019 神工众志科技有限公司
 * {在这里补充类的功能说明}
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
@Slf4j
@Service
public class RabbitMQRegister implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        log.info("在所有类初始化之后调用");
    }
}
