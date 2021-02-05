package com.afeng.web.module.pay.service.impl;

import com.afeng.web.module.pay.model.PayResult;
import com.afeng.web.module.pay.service.PayService;
import com.github.binarywang.wxpay.bean.entpay.*;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 企业付款到零钱
     * <pre>
     * 企业付款业务是基于微信支付商户平台的资金管理能力，为了协助商户方便地实现企业向个人付款，针对部分有开发能力的商户，提供通过API完成企业付款的功能。
     * 比如目前的保险行业向客户退保、给付、理赔。
     * 企业付款将使用商户的可用余额，需确保可用余额充足。查看可用余额、充值、提现请登录商户平台“资金管理”https://pay.weixin.qq.com/进行操作。
     * 注意：与商户微信支付收款资金并非同一账户，需要单独充值。
     * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
     * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers
     * </pre>
     *
     * @param openId 用户
     * @param amount 金额，注意这里入参单位为元
     */
    @Override
    public PayResult wxEntPay(String openId, BigDecimal amount) throws Exception {
        EntPayRequest entPayRequest = new EntPayRequest();
        /*
         * 商户订单号，需保持唯一性 如 10000098201411111234567890 String(32)
         * (只能是字母或者数字，不能包含有其他字符)
         */
        String partnerTradeNo = "" + System.currentTimeMillis() + (int) (100001 + Math.random() * 999999);
        entPayRequest.setPartnerTradeNo(partnerTradeNo);
        entPayRequest.setOpenid(openId);
        /*
         * 校验用户姓名选项
         * NO_CHECK：不校验真实姓名
         * FORCE_CHECK：强校验真实姓名
         */
        entPayRequest.setCheckName("NO_CHECK");
        entPayRequest.setAmount(Integer.valueOf(amount.multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()));//企业付款金额，单位为分
        entPayRequest.setDescription("提现红包");//String(100) 企业付款自定义备注（用户可见），必填。注意：备注中的敏感词会被转成字符*
        //获取本机ip
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        entPayRequest.setSpbillCreateIp(ip);//该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP
        String errCodeDes = null;
        try {
            EntPayResult entPayResult = wxPayService.getEntPayService().entPay(entPayRequest);
            if (StringUtils.isNotBlank(entPayResult.getPaymentNo())) {
                //支付成功，返回支付流水单号
                PayResult result = new PayResult();
                result.setSuccess(true);
                result.setPaymentNo(entPayResult.getPaymentNo());
                return result;
            }
        } catch (WxPayException e) {
            log.error(e.getCustomErrorMsg(), e);
            errCodeDes = e.getErrCodeDes();
        }
        //支付失败，返回原因
        PayResult result = new PayResult();
        result.setSuccess(false);
        result.setErrCodeDes(errCodeDes);
        return result;
    }

    /**
     * 企业付款到银行卡.
     * <pre>
     * 用于企业向微信用户银行卡付款
     * 目前支持接口API的方式向指定微信用户的银行卡付款。
     * 文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_2
     * 接口链接：https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank
     * </pre>
     *
     * @param request 请求对象
     * @return the ent pay bank result
     * @throws WxPayException the wx pay exception
     */
    @Override
    public EntPayBankResult wxEntPayBank(EntPayBankRequest request) throws Exception {
        return wxPayService.getEntPayService().payBank(request);
    }

    /**
     * 查询企业付款到零钱的结果
     * <pre>
     * 查询企业付款API
     * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
     * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
     * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo
     * </pre>
     *
     * @param partnerTradeNo 商户订单号
     */
    @Override
    public EntPayQueryResult wxQueryEntPay(String partnerTradeNo) throws Exception {
        return wxPayService.getEntPayService().queryEntPay(partnerTradeNo);
    }

    /**
     * 查询企业付款到银行卡的结果
     * <pre>
     * 用于对商户企业付款到银行卡操作进行结果查询，返回付款操作详细结果。
     * 文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_3
     * 接口链接：https://api.mch.weixin.qq.com/mmpaysptrans/query_bank
     * </pre>
     *
     * @param partnerTradeNo 商户订单号
     * @return the ent pay bank query result
     * @throws WxPayException the wx pay exception
     */
    @Override
    public EntPayBankQueryResult wxQueryPayBank(String partnerTradeNo) throws Exception {
        return wxPayService.getEntPayService().queryPayBank(partnerTradeNo);
    }


    /**
     * <pre>
     * 获取RSA加密公钥API.
     * RSA算法使用说明（非对称加密算法，算法采用RSA/ECB/OAEPPadding模式）
     * 1、 调用获取RSA公钥API获取RSA公钥，落地成本地文件，假设为public.pem
     * 2、 确定public.pem文件的存放路径，同时修改代码中文件的输入路径，加载RSA公钥
     * 3、 用标准的RSA加密库对敏感信息进行加密，选择RSA_PKCS1_OAEP_PADDING填充模式
     * （eg：Java的填充方式要选 " RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING"）
     * 4、 得到进行rsa加密并转base64之后的密文
     * 5、 将密文传给微信侧相应字段，如付款接口（enc_bank_no/enc_true_name）
     *
     * 接口默认输出PKCS#1格式的公钥，商户需根据自己开发的语言选择公钥格式
     * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_7&index=4
     * 接口链接：https://fraud.mch.weixin.qq.com/risk/getpublickey
     * </pre>
     *
     * @return the public key
     * @throws WxPayException the wx pay exception
     */
    @Override
    public String wxPublicKey() throws Exception {
        return wxPayService.getEntPayService().getPublicKey();
    }


    /**
     * 发送微信红包给个人用户
     * <pre>
     * 文档详见:
     * 发送普通红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3
     *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack
     * 发送裂变红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_5&index=4
     *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack
     * </pre>
     *
     * @param request 请求对象
     */
    @Override
    public WxPaySendRedpackResult wxSendRedpack(WxPaySendRedpackRequest request) throws WxPayException {
        return wxPayService.getRedpackService().sendRedpack(request);
    }

    /**
     * <pre>
     * 微信支付-申请退款
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
     * </pre>
     *
     * @param request 请求对象
     * @return 退款操作结果
     */
    @Override
    public WxPayRefundResult wxRefund(WxPayRefundRequest request) throws WxPayException {
        return wxPayService.refund(request);
    }


}
