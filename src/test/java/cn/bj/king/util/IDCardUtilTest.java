package cn.bj.king.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class IDCardUtilTest {
    @Test
    public void valid() throws Exception {
        String idcard="31282519920202258D";
        System.out.println(IDCardUtil.valid(idcard));
    }

}