package cn.bj.king.service;

import cn.bj.king.entity.AlipayBean;
import com.alipay.api.AlipayApiException;

public interface AlipayService {
    /**
     * 支付宝支付接口
     * @param alipayBean
     * @return
     */
    String aliPayPC(AlipayBean alipayBean);
}
