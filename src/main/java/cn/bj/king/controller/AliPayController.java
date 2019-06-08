package cn.bj.king.controller;

import cn.bj.king.entity.AlipayBean;
import cn.bj.king.service.AlipayService;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AliPayController {

    @Autowired
    private AlipayService alipayService;

    @RequestMapping(value = "submit")
    public String submit(){
        return "submit_order";
    }

    /**
     * 支付宝网页支付
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @GetMapping(value = "alipay")
    public String alipay(String outTradeNo, String subject, String totalAmount, String body,ModelMap map) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        String result= alipayService.aliPayPC(alipayBean);
        map.addAttribute("result", result);
        return "alipay";
    }

}
