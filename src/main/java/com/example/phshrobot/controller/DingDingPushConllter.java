package com.example.phshrobot.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.DingDingSendMessageDto;
import com.example.phshrobot.dto.TapdParamsDto;
import com.example.phshrobot.service.DingDingSendMessageService;
import com.example.phshrobot.service.TapdHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("dingdingpush")
public class DingDingPushConllter {
    @Autowired
    private TapdHandleService handleService;
    @Autowired
    private DingDingSendMessageService dingDingSendMessageService;

    @RequestMapping("tapdWebhook")
    public void tapdWebhook(@RequestBody JSONObject data,
                            @RequestParam(value = "accessToken") String accessToken,
                            @RequestParam(value = "secret") String secret,
                            @RequestParam(value = "type", defaultValue = "text") String type
    ) {
        System.out.println(data.toJSONString());
        if (data == null) {
            return;
        }
        DingDingMessageDto messageDto = handleService.messageHandle(
                TapdParamsDto.builder()
                        .data(data)
                        .type(type)
                        .build());
        if (messageDto == null) {
            return;
        }
        dingDingSendMessageService.sendMessage(
                DingDingSendMessageDto.builder()
                        .accessToken(accessToken)
                        .secret(secret)
                        .messageDto(messageDto)
                        .build());

    }

    
}
