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
        String stringToSign = timestamp + "\n" + sendMessageDto.getSecret();
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

    public static void main(String[] args) {
        Long timestamp = System.currentTimeMillis();
        String secret = "SEC5c42d3f27368c7addbc0c5f6a79eac6774aef56672d63486ba2f741ed5258e15";
        String stringToSign = timestamp + "\n" + secret;
        String token = "92c7b6130098fb1de238167dbd43d9371e4c028f2233d81c1169a068d483b5c8";
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            String url = "https://oapi.dingtalk.com/robot/send";
            DingTalkClient client = new DefaultDingTalkClient(StrFormatter.format("{}?access_token={}&timestamp={}&sign={}", url, token, timestamp, sign));
            OapiRobotSendRequest request = new OapiRobotSendRequest();

            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent("1111");
            request.setText(text);

            OapiRobotSendResponse response = client.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
