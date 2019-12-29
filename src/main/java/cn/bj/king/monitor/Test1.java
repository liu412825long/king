package cn.bj.king.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Test1 implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        log.info("在所有类初始化之后调用");
    }
}
