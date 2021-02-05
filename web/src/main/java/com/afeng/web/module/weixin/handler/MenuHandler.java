package com.afeng.web.module.weixin.handler;

import com.afeng.web.module.weixin.utils.SubscribeMsgUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.EventType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Slf4j
@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxService,
                                    WxSessionManager sessionManager) {


        if (EventType.CLICK.equals(wxMessage.getEvent())) {
            if ("BTN1_KF".equals(wxMessage.getEventKey())) {
                try {
                    SubscribeMsgUtils.sendImage(wxService, wxMessage.getFromUser(), "xepYxPoi12PNU6Zi6J8dmFAEh9okPXK982YIMUIBeXRI48KLSpfxjhuhRsFOpQ_a");
                    String msg = "工作日9:30-11:30,13:30-17:30，其他不定时在线。\n" +
                            "请留下详细问题描述，客服会尽快回复！！";
                    SubscribeMsgUtils.sendMsg(wxService, wxMessage.getFromUser(), msg);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        return null;

//        String msg = String.format("type:%s, event:%s, key:%s",
//                wxMessage.getMsgType(), wxMessage.getEvent(),
//                wxMessage.getEventKey());
//        return WxMpXmlOutMessage.TEXT().content(msg)
//            .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
//            .build();
    }

}
