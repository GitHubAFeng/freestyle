package com.afeng.web.common.util;

import com.afeng.web.common.cache.JedisUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AliyunSmsUtil {


    /**
     * todo 开发者自己的AK(在阿里云访问控制台寻找)
     */
    private static final String ACCESS_KEY_ID = "XXXX";

    /**
     * todo accessKeySecret(在阿里云访问控制台寻找)
     */
    private static final String ACCESS_KEY_SECRET = "XXXXX";

    /**
     * todo 必填:短信签名
     */
    private static final String SIGN_NAME = "XXXX";

    /**
     * todo 必填:短信模板
     */
    private static final String Template_Code = "XXXX";

    /**
     * todo 必填:REGION_ID
     */
    private static final String REGION_ID = "cn-hangzhou";


    private static final String CacheKeyPrefix = "smsCode:";


    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @author AFeng
     * @createDate 2021/2/1 11:05
     **/
    public void send(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            log.warn("【SMS】mobile不可空");
            return;
        }
        Map<String, String> paramMap = new HashMap<>();
        String code = createRandomCode();
        paramMap.put("code", code);
        String templateParam = com.alibaba.fastjson.JSONObject.toJSONString(paramMap);
        sendSMS(mobile, null, templateParam);
        JedisUtil.getInstance().set(CacheKeyPrefix + mobile, code);
    }

    /**
     * 验证
     *
     * @param mobile 手机号
     * @param code   验证码
     * @author AFeng
     * @createDate 2021/2/1 11:05
     **/
    public boolean verify(String mobile, String code) {
        if (StringUtils.isBlank(mobile)) {
            log.warn("【SMS】mobile不可空");
            return false;
        }
        String cacheCode = JedisUtil.getInstance().get(CacheKeyPrefix + mobile);
        if (StringUtils.isBlank(cacheCode)) {
            log.warn("【SMS】缓存不存在 verify({}, {})", mobile, code);
            return false;
        }
        // 移除缓存
        JedisUtil.getInstance().del(CacheKeyPrefix + mobile);
        return StringUtils.equals(cacheCode, code);
    }

    /**
     * @param phoneNumbers  待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
     * @param templateCode  短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
     * @param templateParam 模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\", \"code\":\"123\"}"
     */
    public static void sendSMS(String phoneNumbers, String templateCode, String templateParam) {
        try {
            // 设置超时时间-可自行调整
//            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 使用post提交
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(phoneNumbers);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            if (StringUtils.isBlank(templateCode)) templateCode = Template_Code;
            request.setTemplateCode(templateCode);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam(templateParam);
            // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            // request.setSmsUpExtendCode("90997");
            // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            // request.setOutId("yourOutId");
            // 请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                log.debug("【SMS】阿里云发送短信成功，code:{},message:{},requestId:{},bizId:{}", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            } else {
                log.warn("【SMS】阿里云发送失败，code:{},message:{},requestId:{},bizId:{},mobile:{},signName:{},templateCode:{},templateParam:{}", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId(), phoneNumbers, SIGN_NAME, templateCode, templateParam);
            }
        } catch (Exception e) {
            log.error("【SMS】阿里云发送短信失败:{},mobile:{},signName:{},templateCode:{},templateParam:{}", e.getMessage(), phoneNumbers, SIGN_NAME, templateCode, templateParam);
        }
    }

    /**
     * 生成4位随机数
     */
    private static String createRandomCode() {
        //验证码
        StringBuilder vcode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            vcode.append((int) (Math.random() * 9));
        }
        return vcode.toString();
    }


}
