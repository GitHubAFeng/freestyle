package com.afeng.web.module.pay.model;

/**
 * 支付结果，isSuccess为true则获取单号paymentNo，否则获取原因errCodeDes
 *
 * @author AFeng
 * @version date: 2021/2/5 11:04
 **/
public class PayResult {
    private boolean isSuccess;
    private String paymentNo;
    private String errCodeDes;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

}
