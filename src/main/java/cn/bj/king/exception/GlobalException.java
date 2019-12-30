/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package cn.bj.king.exception;


import cn.bj.king.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 统一 Exception 对象
 * ~ Controller AOP 可直接拦截该对象，获取信息返回前端
 *
 * @author yiliang.gyl
 * @version $Id: AppException.java, v 0.1 Jun 21, 2015 10:57:19 AM yiliang.gyl
 *          Exp $
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 4925495525292795163L;

    /**
     * 异常结果码
     */
    private int code;
    /**
     * 异常结果信息
     */
    private String message;
    /**
     * http 返回码
     */
    private int statusCode;

    /**
     * @param statusCode
     * @param errorCodeEnum
     * @param message
     */
    public GlobalException(ErrorCodeEnum errorCodeEnum, String message, int statusCode) {
        super(message);
        this.code = errorCodeEnum.getCode();
        this.statusCode = statusCode;
        if (StringUtils.isEmpty(message)) {
            this.message = errorCodeEnum.getDefaultMessage();
        } else {
            this.message = message;
        }
    }

    /**
     * 传递错误码和错误消息
     *
     * @param errorCodeEnum
     * @param message
     */
    public GlobalException(ErrorCodeEnum errorCodeEnum, String message) {
        super(message);
        this.code = errorCodeEnum.getCode();
        this.message = message;
    }

    /**
     * 传递错误码和错误消息
     *
     * @param errorCode
     * @param message
     */
    public GlobalException(int errorCode, String message) {
        super(message);
        this.code = errorCode;
        this.message = message;
    }

    /**
     * 传递错误码和错误消息
     *
     * @param errorCode
     */
    public GlobalException(ErrorCodeEnum errorCode) {
        super();
        this.code = errorCode.getCode();
        this.message = errorCode.getDefaultMessage();
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property <tt>statusCode</tt>.
     *
     * @return property value of statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Setter method for property <tt>statusCode</tt>.
     *
     * @param statusCode value to be assigned to property statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @see Throwable#toString()
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (StringUtils.isEmpty(message)) ? (s + ": " + message) : (s + ": " + code + "。");
    }
}
