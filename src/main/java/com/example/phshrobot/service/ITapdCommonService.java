package com.example.phshrobot.service;

import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.TapdEventParamsDto;

public interface ITapdCommonService {
    DingDingMessageDto getMessageData(TapdEventParamsDto eventParamsDto);
}
