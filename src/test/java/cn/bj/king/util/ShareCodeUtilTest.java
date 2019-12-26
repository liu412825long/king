package cn.bj.king.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShareCodeUtilTest {
    @Test
    public void toSerialCode() throws Exception {

        long sourceId=8746225567453323656L;
        String shareCode=ShareCodeUtil.toSerialCode(sourceId,16);
        Long toId=ShareCodeUtil.codeToId(shareCode);
        System.out.println(sourceId+"--->"+shareCode+"--->"+toId);

    }

}