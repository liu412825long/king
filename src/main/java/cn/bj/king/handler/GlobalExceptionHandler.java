package cn.bj.king.handler;

import cn.bj.king.base.ResponseMessage;
import cn.bj.king.enums.ErrorCodeEnum;
import cn.bj.king.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Create by 刘海龙 2019年12月30日
 * Copyright (c) 2019 神工众志科技有限公司
 * Controller异常处理类
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ResponseMessage customerException(GlobalException e){
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.setCode(e.getCode());
        responseMessage.setMessage(e.getMessage());
        return responseMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage globalException(Exception e){
        if(log.isDebugEnabled()){
            e.printStackTrace();
        }
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.setCode(ErrorCodeEnum.SYSTEM_EXCEPTION.getCode());
        responseMessage.setMessage(ErrorCodeEnum.SYSTEM_EXCEPTION.getDefaultMessage());
        return responseMessage;
    }
}
