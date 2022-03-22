
package com.example.phshrobot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.phshrobot.constant.dingding.DingDingSendTypeConstant;
import com.example.phshrobot.constant.tapd.BugCommonEnums;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.TapdEventParamsDto;
import com.example.phshrobot.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_FORMAT;
import static com.example.phshrobot.constant.tapd.TapdEventKeysConstant.*;

@Service
public class TapdScheduleEventService implements ITapdCommonService {

    @Override
    public DingDingMessageDto getMessageData(TapdEventParamsDto eventParamsDto) {
        DingDingMessageDto messageDto = null;
        String toKey = eventParamsDto.getEventKey() + eventParamsDto.getType();
        switch (toKey) {
            case EVENTS_TAG_SCHEDULE_TIMER + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
                messageDto = getScheduleTextMessage(eventParamsDto.getActions());
                break;
            case EVENTS_TAG_SCHEDULE_TIMER + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
                messageDto = getScheduleMdMessage(eventParamsDto.getActions());
                break;
            default:
                break;
        }
        return messageDto;
    }


    private DingDingMessageDto getScheduleTextMessage(JSONObject json) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            JSONObject toObj = JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()));
            List<String> list = Arrays.asList(EVENTS_TYPE_STORY, EVENTS_TYPE_BUG, EVENTS_TYPE_TASK);
            for (String toKey : list) {
                JSONArray array = toObj.getJSONArray(toKey);
                String str = fomatStrText(toKey, array);
                if (StrUtil.isNotBlank(str)) {
                    builder.append(str);
                }
            }
        }


        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title("超时提醒 : ")
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)
                .build();

    }

    public String fomatStrText(String key, JSONArray array) {
        if (array == null || array.size() == 0) {
            return "";
        }
        String title = "";
        switch (key) {
            case EVENTS_TYPE_STORY:
                title = "需求";
                break;
            case EVENTS_TYPE_BUG:
                title = "BUG";
                break;
            case EVENTS_TYPE_TASK:
                title = "任务";
                break;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("存在超时{}: ",
                title
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("提醒时间: {}",
                DateUtil.format(new Date(), NORM_DATETIME_FORMAT)
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("项目已有{}个【{}】超时了，请及时处理。",
                array.size(), title
        ));
        builder.append("\n");
        return builder.toString();
    }

    public String fomatStrMd(String key, JSONArray array) {
        if (array == null || array.size() == 0) {
            return "";
        }
        String title = "";
        switch (key) {
            case EVENTS_TYPE_STORY:
                title = "需求";
                break;
            case EVENTS_TYPE_BUG:
                title = "BUG";
                break;
            case EVENTS_TYPE_TASK:
                title = "任务";
                break;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("##### 存在超时{}: ",
                title
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 提醒时间: {}",
                DateUtil.format(new Date(), NORM_DATETIME_FORMAT)
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 项目已有<font color='#FF0000'>{}</font>个【<font color='#FF0000'>{}</font>】超时了，请及时处理。",
                array.size(), title
        ));
        builder.append("\n");
        return builder.toString();
    }

    private DingDingMessageDto getScheduleMdMessage(JSONObject json) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            JSONObject toObj = JSONObject.parseObject(JSONObject.toJSONString(entry.getValue()));
            List<String> list = Arrays.asList(EVENTS_TYPE_STORY, EVENTS_TYPE_BUG, EVENTS_TYPE_TASK);
            for (String toKey : list) {
                JSONArray array = toObj.getJSONArray(toKey);
                String str = fomatStrMd(toKey, array);
                if (StrUtil.isNotBlank(str)) {
                    builder.append(str);
                }
            }
        }


        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title("超时提醒 : ")
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)
                .build();

    }

}
