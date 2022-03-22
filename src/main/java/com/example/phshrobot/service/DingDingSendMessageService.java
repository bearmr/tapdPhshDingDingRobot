package com.example.phshrobot.service;

import cn.hutool.core.text.StrFormatter;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.example.phshrobot.constant.dingding.DingDingSendTypeConstant;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.DingDingSendMessageDto;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

import java.util.Arrays;

@Service
public class DingDingSendMessageService {
    public void sendMessage(DingDingSendMessageDto sendMessageDto) {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "" + sendMessageDto.getSecret();
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(sendMessageDto.getSecret().getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            String url = "https://oapi.dingtalk.com/robot/send";
            DingTalkClient client = new DefaultDingTalkClient(StrFormatter.format("{}?access_token={}&timestamp={}&sign={}", url, sendMessageDto.getAccessToken(), timestamp, sign));
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            DingDingMessageDto messageDto = sendMessageDto.getMessageDto();
            if (messageDto.getType().equals(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)) {
                request.setMsgtype("text");
                OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
                text.setContent(messageDto.getText());
                request.setText(text);
            } else if (messageDto.getType().equals(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)) {
                request.setMsgtype("markdown");
                OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
                markdown.setTitle(messageDto.getTitle());
                markdown.setText(messageDto.getText());
                request.setMarkdown(markdown);
            }
            OapiRobotSendResponse response = client.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test() {
        DingTalkClient client = new DefaultDingTalkClient("自己的钉钉地址");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");

        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
        // isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(true);
        at.setAtUserIds(Arrays.asList("109929", "32099"));
        request.setAt(at);

        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();

        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
        request.setLink(link);

//        request.setAt();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);

        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
