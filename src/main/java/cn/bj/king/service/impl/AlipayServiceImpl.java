package cn.bj.king.service.impl;

import cn.bj.king.config.AliPayConfig;
import cn.bj.king.entity.AlipayBean;
import cn.bj.king.service.AlipayService;
import cn.bj.king.util.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayServiceImpl implements AlipayService {

    private static Logger logger= LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private AliPayConfig aliPayConfig;

    @Override
    public String aliPayPC(AlipayBean alipayBean){
        // 1、获得初始化的AlipayClient
        String serverUrl = aliPayConfig.getGatewayUrl();
        String appId = aliPayConfig.getAppId();
        String privateKey = aliPayConfig.getPrivateKey();
        String format = "json";
        String charset = aliPayConfig.getCharset();
        String publicKey = aliPayConfig.getPublicKey();
        String signType = aliPayConfig.getSignType();
        String returnUrl = aliPayConfig.getReturnUrl();
        String notifyUrl = aliPayConfig.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.stringify(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("支付宝构造表单失败！！！");
        }
        // 返回付款信息
        return result;
    }
}
