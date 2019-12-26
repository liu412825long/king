package cn.bj.king.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SerialNumberUtilTest {
    @Test
    public void generaterNextNumber() throws Exception {
        String code=SerialNumberUtil.getInstance().generaterNextNumber("2019052200000002");
        System.out.println(code);
    }

}