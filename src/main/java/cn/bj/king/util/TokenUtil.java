package cn.bj.king.util;

import java.util.UUID;

/**
 * Create by 刘海龙 2019年12月30日
 * Copyright (c) 2019 神工众志科技有限公司
 * {在这里补充类的功能说明}
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
public class TokenUtil {
    /**
     * 创建一个token字符串
     * @return
     */
    public static String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
