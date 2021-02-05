package com.afeng.web.module.weixin.handler;

import com.afeng.web.module.weixin.utils.SubscribeMsgUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注事件
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("关注事件SubscribeHandler");
        this.logger.info("公众号: " + wxMessage.getToUser());
        this.logger.info("事件类型: " + wxMessage.getEvent());
        this.logger.info("事件KEY值: " + wxMessage.getEventKey());
        this.logger.info("消息类型: " + wxMessage.getMsgType());
        this.logger.info("消息内容: " + wxMessage.getContent());
        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        //回复
        SubscribeMsgUtils.onSubscribeEvent(wxMpService, wxMessage.getFromUser());

        return null;
    }


}
