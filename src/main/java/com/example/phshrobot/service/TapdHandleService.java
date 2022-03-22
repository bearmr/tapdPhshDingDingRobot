package com.example.phshrobot.service;

import com.alibaba.fastjson.JSONObject;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.TapdEventParamsDto;
import com.example.phshrobot.dto.TapdParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.phshrobot.constant.tapd.TapdEventKeysConstant.*;

/**
 * 创建对应
 */
@Service
public class TapdHandleService {
    @Autowired
    private TapdBugEventService bugEventService;
    @Autowired
    private TapdTaskEventService taskEventService;
    @Autowired
    private TapdStoryEventService storyEventService;
    @Autowired
    private TapdScheduleEventService scheduleEventService;

    public DingDingMessageDto messageHandle(TapdParamsDto paramsDto) {
        JSONObject event = paramsDto.getData().getJSONObject("event");
        String objType = event.getString("object_type");
        DingDingMessageDto messageDto = null;
        TapdEventParamsDto eventParamsDto = TapdEventParamsDto.builder()
                .eventKey(event.getString("event_key"))
                .type(paramsDto.getType())
                .events(paramsDto.getData()
                        .getJSONObject("events")
                        .getJSONObject(event.getString("event_key"))
                )
                .build();
        try {
            eventParamsDto.setActions(paramsDto.getData().getJSONObject("actions"));
        } catch (Exception e) {

        }
        switch (objType) {
            case EVENTS_TYPE_BUG:
                messageDto = bugEventService.getMessageData(eventParamsDto);
                break;
            case EVENTS_TYPE_TASK:
                messageDto = taskEventService.getMessageData(eventParamsDto);
                break;
            case EVENTS_TYPE_STORY:
                messageDto = storyEventService.getMessageData(eventParamsDto);
                break;
            case EVENTS_TYPE_SCHEDULE:
                messageDto = scheduleEventService.getMessageData(eventParamsDto);
                break;
        }
        return messageDto;
    }


}
