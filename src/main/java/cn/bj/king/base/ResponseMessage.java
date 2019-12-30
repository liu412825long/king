package cn.bj.king.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据返回实体
 * 返回信息包括 业务代码, 业务消息(有本地化实现), 业务数据, HTTP 错误码, HTTP Header
 *
 * @author Eric
 * @date 2018/3/16
 */
@Setter
@Getter
@NoArgsConstructor
//@XmlRootElement
public class ResponseMessage<T> implements Serializable {
    /**
     * 待返回的业务代码(错误代码)
     */
    private int code;

    /**
     * 待返回的消息内容
     * <p>
     */
    private String message="success";

    /**
     * 待返回的头部信息
     */
    @JsonIgnore
    private transient List<HttpHeader> headers;

    /**
     * 待返回的数据
     */
    private T data;

    /**
     * 默认返回code码, 0 = OK
     */
    private static final int DEFAULT_CODE = 200;

    private static final String HEADER_TOKEN_FIELD = "X-Security-Token";

    public ResponseMessage(Integer code) {
        this.code = code;
    }

    public ResponseMessage(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 设置 X-Security-Token
     *
     * @param token
     * @return
     */
    public ResponseMessage token(String token) {
        return header(HEADER_TOKEN_FIELD, token);
    }

    /**
     * 设置HTTP头部信息
     *
     * @param key
     * @param value
     * @return
     */
    public ResponseMessage header(String key, String value) {

        if (headers == null) {
            headers = new ArrayList<>();
        }

        boolean flag = true;

        for (HttpHeader header : headers) {
            if (header.getKey().toLowerCase().equals(key.toLowerCase())) {
                header.setValue(value);
                flag = false;
                break;
            }
        }

        if (flag) {
            headers.add(new HttpHeader(key, value));
        }

        return this;
    }

    /**
     * 设置错误代码
     *
     * @param code
     * @return
     */
    public ResponseMessage code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 设置返回数据
     *
     * @param data
     * @return
     */
    public ResponseMessage data(T data) {
        this.data = data;
        return this;
    }

    public static ResponseMessage build() {
        return new ResponseMessage(DEFAULT_CODE);
    }

    public static ResponseMessage build(Integer code) {
        return new ResponseMessage(code);
    }

    public static ResponseMessage build(Integer code, Object data) {
        return new ResponseMessage(code, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HttpHeader {
        private String key;
        private String value;
    }

}
