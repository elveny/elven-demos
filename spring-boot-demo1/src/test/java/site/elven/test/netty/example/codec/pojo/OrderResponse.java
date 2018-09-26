package site.elven.test.netty.example.codec.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class OrderResponse implements Serializable {
    private final static long serialVersionUID = 1L;
    private String orderNo;
    private String code;
    private String message;

    public OrderResponse() {
    }

    public OrderResponse(String orderNo, String code, String message) {
        this.orderNo = orderNo;
        this.code = code;
        this.message = message;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
