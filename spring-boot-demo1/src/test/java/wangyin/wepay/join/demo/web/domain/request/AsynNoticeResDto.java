package wangyin.wepay.join.demo.web.domain.request;

import java.io.Serializable;

/**
 * Created by wyhuangtongshuan on 15-2-10.
 */
public class AsynNoticeResDto implements Serializable {
    private String TYPE;//交易类型
    private String ID;//订单号
    private String AMOUNT;//交易金额
    private String CURRENCY;//币种
    private String DATE;//日期
    private String TIME;//时间
    private String NOTE;//NOTE
    private String STATUS;//支付状态
    private String CODE;//支付结果码
    private String DESC;//支付结果描述

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    @Override
    public String toString() {
        return "AsynNoticeResDto{" +
                "TYPE='" + TYPE + '\'' +
                ", ID='" + ID + '\'' +
                ", AMOUNT='" + AMOUNT + '\'' +
                ", CURRENCY='" + CURRENCY + '\'' +
                ", DATE='" + DATE + '\'' +
                ", TIME='" + TIME + '\'' +
                ", NOTE='" + NOTE + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", CODE='" + CODE + '\'' +
                ", DESC='" + DESC + '\'' +
                '}';
    }
}
