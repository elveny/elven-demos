package site.elven.test.netty.example.codec.msgpack;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
public class OrderRequest implements Serializable {
    private final static long serialVersionUID = 1L;
    private String orderNo;
    private String userName;
    private String phoneNumber;
    private String address;
    private String productName;
    private int productNumber;

    public OrderRequest() {
    }

    public OrderRequest(String orderNo, String userName, String phoneNumber, String address, String productName, int productNumber) {
        this.orderNo = orderNo;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productName = productName;
        this.productNumber = productNumber;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
