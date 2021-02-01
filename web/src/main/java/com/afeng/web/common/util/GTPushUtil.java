package com.afeng.web.common.util;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.CommonEnum;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.message.PushBatchDTO;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.req.message.PushMessage;
import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://docs.getui.com/getui/server/rest_v2/common_args/?id=doc-title-6
 *
 * @author AFeng
 * @createDate 2021/2/1 14:52
 **/
@Slf4j
public class GTPushUtil {

    private static ApiHelper apiHelper = null;
    private static PushApi pushApi = null;

    private ApiHelper init() {
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        //填写应用配置
        apiConfiguration.setAppId("xxx");
        apiConfiguration.setAppKey("xxx");
        apiConfiguration.setMasterSecret("xxx");
        // 接口调用前缀，请查看文档: 接口调用规范 -> 接口前缀, 可不填写appId
        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        // 实例化ApiHelper对象，用于创建接口对象
        return ApiHelper.build(apiConfiguration);
    }

    public ApiHelper getApiHelper() {
        if (apiHelper == null) apiHelper = init();
        return apiHelper;
    }

    public PushApi getPushApi() {
        if (pushApi == null) {
            // 创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
            pushApi = getApiHelper().creatApi(PushApi.class);
        }
        return pushApi;
    }

    /**
     * 向单个用户推送消息，可根据cid指定用户
     * 该消息在用户在线时推送个推通道，用户离线时推送厂商通道
     *
     * @param cid           cid
     * @param title         通知栏标题
     * @param body          通知栏内容
     * @param clickTypeEnum 点击通知后续动作
     * @param url           点击通知打开链接
     * @param intent        点击通知打开应用特定页面，intent格式必须正确且不能为空
     * @param payload       点击通知加自定义消息
     * @author AFeng
     * @createDate 2021/2/1 15:21
     **/
    public void push(String cid, String title, String body, CommonEnum.ClickTypeEnum clickTypeEnum, String url, String intent, String payload) {
        if (StringUtils.isBlank(cid)) {
            log.warn("【个推】发送失败 cid不可空");
            return;
        }
        //根据cid进行单推
        PushDTO<Audience> pushDTO = new PushDTO<>();
        // 设置推送参数
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        PushMessage pushMessage = new PushMessage();
        pushDTO.setPushMessage(pushMessage);
        GTNotification notification = new GTNotification();
        notification.setTitle(title);
        notification.setBody(body);
        notification.setClickType(clickTypeEnum.type);
        if (CommonEnum.ClickTypeEnum.TYPE_URL.type.equals(clickTypeEnum.type)) notification.setUrl(url);
        if (CommonEnum.ClickTypeEnum.TYPE_INTENT.type.equals(clickTypeEnum.type)) notification.setIntent(intent);
        if (CommonEnum.ClickTypeEnum.TYPE_PAYLOAD.type.equals(clickTypeEnum.type)
                || CommonEnum.ClickTypeEnum.TYPE_PAYLOAD_CUSTOM.type.equals(clickTypeEnum.type))
            notification.setPayload(payload);
        pushMessage.setNotification(notification);

        // 设置接收人信息 audience 推送目标用户
        Audience audience = new Audience();
        pushDTO.setAudience(audience);
        audience.addCid(cid);

        // 进行cid单推
        ApiResult<Map<String, Map<String, String>>> apiResult = getPushApi().pushToSingleByCid(pushDTO);
        if (apiResult.isSuccess()) {
            // success
            log.debug("【个推】发送成功 data:" + apiResult.getData());
        } else {
            // failed
            log.warn("【个推】发送失败 code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }

    /**
     * 向多个用户推送消息，可根据cid指定用户
     * 该消息在用户在线时推送个推通道，用户离线时推送厂商通道
     *
     * @param cidList       cid数组
     * @param title         通知栏标题
     * @param body          通知栏内容
     * @param clickTypeEnum 点击通知后续动作
     * @param url           点击通知打开链接
     * @param intent        点击通知打开应用特定页面，intent格式必须正确且不能为空
     * @param payload       点击通知加自定义消息
     * @author AFeng
     * @createDate 2021/2/1 15:21
     **/
    public void push(List<String> cidList, String title, String body, CommonEnum.ClickTypeEnum clickTypeEnum, String url, String intent, String payload) {
        if (CollectionUtils.isEmpty(cidList)) {
            log.warn("【个推】发送失败 cid不可空");
            return;
        }
        PushBatchDTO pushBatchDTO = new PushBatchDTO();
        List<PushDTO<Audience>> msgList = new ArrayList<>();
        PushDTO<Audience> pushDTO;
        PushMessage pushMessage;
        GTNotification notification;
        for (String cid : cidList) {
            pushDTO = new PushDTO<>();
            // 设置推送参数
            pushDTO.setRequestId(System.currentTimeMillis() + "");
            pushMessage = new PushMessage();
            pushDTO.setPushMessage(pushMessage);
            notification = new GTNotification();
            notification.setTitle(title);
            notification.setBody(body);
            notification.setClickType(clickTypeEnum.type);
            if (CommonEnum.ClickTypeEnum.TYPE_URL.type.equals(clickTypeEnum.type)) notification.setUrl(url);
            if (CommonEnum.ClickTypeEnum.TYPE_INTENT.type.equals(clickTypeEnum.type)) notification.setIntent(intent);
            if (CommonEnum.ClickTypeEnum.TYPE_PAYLOAD.type.equals(clickTypeEnum.type)
                    || CommonEnum.ClickTypeEnum.TYPE_PAYLOAD_CUSTOM.type.equals(clickTypeEnum.type))
                notification.setPayload(payload);
            pushMessage.setNotification(notification);

            // 设置接收人信息 audience 推送目标用户
            Audience audience = new Audience();
            pushDTO.setAudience(audience);
            audience.addCid(cid);
            msgList.add(pushDTO);
        }

        pushBatchDTO.setMsgList(msgList);
        ApiResult<Map<String, Map<String, String>>> apiResult = getPushApi().pushBatchByCid(pushBatchDTO);
        if (apiResult.isSuccess()) {
            // success
            log.debug("【个推】发送成功 data:" + apiResult.getData());
        } else {
            // failed
            log.warn("【个推】发送失败 code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }

    /*
     * 点击通知后续动作，目前支持以下后续动作
     *         intent,//打开应用内特定页面
     *         url,//打开网页地址
     *         payload,//自定义消息内容启动应用
     *         payload_custom,//自定义消息内容不启动应用
     *         startapp,//打开应用首页
     *         none,//纯通知，无后续动作
     */


}
