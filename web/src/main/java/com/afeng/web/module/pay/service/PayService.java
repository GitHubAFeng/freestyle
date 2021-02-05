package com.afeng.web.module.pay.service;

import com.afeng.web.module.pay.model.PayResult;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankQueryResult;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankResult;
import com.github.binarywang.wxpay.bean.entpay.EntPayQueryResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.math.BigDecimal;

public interface PayService {

    /**
     * @param openId 用户
     * @param amount 金额，注意这里入参单位为元
     * @author AFeng
     * @date 2021/2/4 15:45
     * @deprecated 企业付款到零钱
     **/
    PayResult wxEntPay(String openId, BigDecimal amount) throws Exception;

    EntPayBankResult wxEntPayBank(EntPayBankRequest request) throws Exception;

    EntPayQueryResult wxQueryEntPay(String partnerTradeNo) throws Exception;

    EntPayBankQueryResult wxQueryPayBank(String partnerTradeNo) throws Exception;

    String wxPublicKey() throws Exception;

    WxPaySendRedpackResult wxSendRedpack(WxPaySendRedpackRequest request) throws WxPayException;

    WxPayRefundResult wxRefund(WxPayRefundRequest request) throws WxPayException;
}
