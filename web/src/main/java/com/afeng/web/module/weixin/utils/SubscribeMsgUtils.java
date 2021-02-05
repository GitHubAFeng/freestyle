package com.afeng.web.module.weixin.utils;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

@Slf4j
public class SubscribeMsgUtils {

    public static void onSubscribeEvent(WxMpService wxService, String openId) {
        try {
            String msg = "来了~小可爱\uD83C\uDF39~\n";
            SubscribeMsgUtils.sendMsg(wxService, openId, msg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 主动发送客服消息
     *
     * @param msg 文本
     * @author AFeng
     * @createDate 2020/11/16 17:53
     **/
    public static void sendMsg(WxMpService wxService, String openId, String msg) throws Exception {
        WxMpKefuMessage message = new WxMpKefuMessage();
        message.setMsgType(WxConsts.KefuMsgType.TEXT);
        message.setToUser(openId);
        message.setContent(msg);
        wxService.getKefuService().sendKefuMessage(message);
    }


    /**
     * 发送图片
     *
     * @param openId
     * @author AFeng
     * @createDate 2020/11/16 18:01
     */
    public static void sendImage(WxMpService wxService, String openId, String mediaId) throws Exception {
        WxMpKefuMessage message = new WxMpKefuMessage();
        message.setMsgType(WxConsts.KefuMsgType.IMAGE);
        message.setToUser(openId);
        message.setMediaId(mediaId);
        wxService.getKefuService().sendKefuMessage(message);
    }


}
