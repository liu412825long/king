package cn.bj.king.util;

import java.util.Date;
import java.util.Random;

public class OrderCodeUtil {

    public static String CODE_PREFIX="RK";
    public static void main(String[] args) {
        System.out.println(createOrderCode());
    }

    public static synchronized String createOrderCode(){
        Random random=new Random();
        int ran=(int)(Math.random()*100000);
        Date date=new Date();
        System.out.println(ran);
        return CODE_PREFIX+date.getTime()+ran;
    }
}
