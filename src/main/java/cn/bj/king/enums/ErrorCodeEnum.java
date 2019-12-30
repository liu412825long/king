/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package cn.bj.king.enums;

import java.text.MessageFormat;

/**
 * 错误码枚举类
 * 1. 错误码获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getCode();
 * 2. 默认错误信息获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getDefaultMessage(); >> 参数值个数超过最大限制
 * 3. 自定义的错误信息获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getMessage("mobile_no","50"); >>mobile_no参数值最多只支持50个
 * <b>推荐使用自定义的错误信息方式来生成详细的错误提示信息。</b>
 *
 * @author yiliang.gyl
 * @version $Id: ErrorCodeEnum.java, v 0.1 Jun 21, 2015 10:54:14 AM yiliang.gyl Exp $
 */
public enum ErrorCodeEnum {
    /**
     * 参数类异常
     */
    PARAMETERS_IS_NULL(1100, "输入参数不能为空", "输入参数({0})不能为空"),

    ACCOUNT_NOT_EXIST(1201,"账户不存在","账户不存在"),

    BIZ_STATUS_ERROR(816, "该状态不能对项目操作", "{0}"),
    BIZ_UNSUPPORTED_KIND(817, "不支持附件类型", "{0}"),
    BIZ_UNSUPPORTED_REGION(818, "系统暂不支持所该地区", "系统暂不支持所该地区"),
    BIZ_UNSUPPORTED(819, "不支持的业务类型", "暂不支持{0}业务类型"),

    /**
     * 系统类异常
     */
    SYSTEM_EXCEPTION(900, "系统处理异常", "系统处理异常。原因描述：{0}"),
    SYSTEM_TIMEOUT(901, "系统处理超时", "系统处理超时"),
    DB_EXCEPTION(902, "数据库处理异常", "数据库处理异常"),
    PAY_EXCEPTION(903,"支付过程出现异常","支付过程出现异常"),

    /**
     * 未知异常
     */
    UNKNOW_ERROR(-1, "未知系统异常", "未知系统异常"),



    /**
     * 没有错误
     */
    NO_ERROR(200, "操作成功", "操作成功");

    private int code;
    private String defaultMessage;
    private String message;

    /**
     * @param code
     * @param defaultMessage
     */
    private ErrorCodeEnum(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    /**
     * @param code
     * @param defaultMessage
     * @param message
     */
    private ErrorCodeEnum(int code, String defaultMessage, String message) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.message = message;
    }

    /**
     * 自定义错误信息
     * <br/>当未支持定义错误信息时，返回默认的message信息
     * <b>请尽量使用该方法来自定义的明确的错误提示信息</b>
     * <p/>
     * <p>
     * 使用方法：
     * ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getMessage("mobile_no","50");
     * >> mobile_no参数值最多只支持50个
     * </p>
     *
     * @param input 自定义描述
     * @return
     */
    public String getMessage(String... input) {
        Object[] obj = input;
        return getMessage(obj);
    }

    /**
     * @param input
     * @return
     */
    private String getMessage(Object[] input) {
        if (input == null || input.length == 0 || getMessage() == null) {
            return getDefaultMessage();
        } else {
            return MessageFormat.format(getMessage(), input);
        }
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
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    private String getMessage() {
        return message;
    }

    /**
     * Getter method for property <tt>formatMessage</tt>.
     *
     * @return property value of formatMessage
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

}
