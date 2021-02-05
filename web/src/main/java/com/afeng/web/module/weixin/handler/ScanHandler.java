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
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        this.logger.info("扫码事件ScanHandler");
        this.logger.info("公众号: " + wxMessage.getToUser());
        this.logger.info("事件类型: " + wxMessage.getEvent());
        this.logger.info("事件KEY值: " + wxMessage.getEventKey());
        this.logger.info("消息类型: " + wxMessage.getMsgType());
        this.logger.info("消息内容: " + wxMessage.getContent());
        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        SubscribeMsgUtils.onSubscribeEvent(wxMpService, wxMessage.getFromUser());

        return null;
    }
}
