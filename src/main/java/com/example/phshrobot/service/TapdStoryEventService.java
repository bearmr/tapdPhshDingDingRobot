package com.example.phshrobot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import com.alibaba.fastjson.JSONObject;
import com.example.phshrobot.constant.dingding.DingDingSendTypeConstant;
import com.example.phshrobot.constant.tapd.StoryCommonEnums;
import com.example.phshrobot.constant.tapd.TapdEventKeysConstant;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.TapdEventParamsDto;
import com.example.phshrobot.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_FORMAT;

@Service
public class TapdStoryEventService implements ITapdCommonService {

    @Override
    public DingDingMessageDto getMessageData(TapdEventParamsDto eventParamsDto) {
        DingDingMessageDto messageDto = null;
        String toKey = eventParamsDto.getEventKey() + eventParamsDto.getType();
        switch (toKey) {
            case TapdEventKeysConstant.EVENTS_TAG_STORY_CREATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
                messageDto = getStoryCraeteTextMessage(eventParamsDto.getEvents());
                break;
            case TapdEventKeysConstant.EVENTS_TAG_STORY_CREATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
                messageDto = getStoryCraeteMdMessage(eventParamsDto.getEvents());
                break;
            case TapdEventKeysConstant.EVENTS_TAG_STORY_CHANGE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
            case TapdEventKeysConstant.EVENTS_TAG_STORY_UPDATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
                messageDto = getStoryChagerTextMessage(eventParamsDto.getEvents());
                break;
            case TapdEventKeysConstant.EVENTS_TAG_STORY_UPDATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
            case TapdEventKeysConstant.EVENTS_TAG_STORY_CHANGE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
                messageDto = getStoryChagerMdMessage(eventParamsDto.getEvents());
                break;
            default:
                break;
        }
        return messageDto;
    }

    public DingDingMessageDto getStoryCraeteTextMessage(JSONObject json) {
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("需求: {} ",
                newTagJson.getString("name")));
        builder.append("\n");
        builder.append(StrFormatter.format("状态: {}",
                StoryCommonEnums.StoryFlowEnums.getKeyByValue(newTagJson.getString("status"))));
        builder.append("\n");
        builder.append(StrFormatter.format("优先级: {}",
                StoryCommonEnums.StoryPriorityEnums.getKeyByValue(newTagJson.getString("priority"))));
        builder.append("\n");
        builder.append(StrFormatter.format("预计开始: {}",
                newTagJson.getString("begin")));
        builder.append("\n");
        builder.append(StrFormatter.format("预计结束: {}",
                newTagJson.getString("due")));
        builder.append("\n");
        builder.append(StrFormatter.format("指派给: {}",
                newTagJson.getString("owner")));
        builder.append("\n");
        builder.append(StrFormatter.format("创建人: {}",
                newTagJson.getString("creator")));
        builder.append("\n");

        builder.append(StrFormatter.format("创建时间: {}",
                DateUtil.format(new Date(json.getLong("timestamp_micro")), NORM_DATETIME_FORMAT)));
        ;
        builder.append("\n");
        builder.append("相关链接:");
        builder.append("\n");
        builder.append(StrFormatter.format("https://www.tapd.cn/{}/prong/stories/view/{}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(newTagJson.getString("name"))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)
                .build();
    }

    public DingDingMessageDto getStoryCraeteMdMessage(JSONObject json) {
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("##### 需求: {} ",
                newTagJson.getString("name")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 状态: {}",
                StoryCommonEnums.StoryFlowEnums.getKeyByValue(newTagJson.getString("status"))));
        builder.append("\n");
        builder.append(StrFormatter.format("- 优先级: <font color='#FF0000'>{}</font>",
                StoryCommonEnums.StoryPriorityEnums.getKeyByValue(newTagJson.getString("priority"))));
        builder.append("\n");
        builder.append(StrFormatter.format("- 预计开始: {}",
                newTagJson.getString("begin")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 预计结束: {}",
                newTagJson.getString("due")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 指派给: <font color='#FF0000'>{}</font>",
                newTagJson.getString("owner")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建人: <font color='#FF0000'>{}</font>",
                newTagJson.getString("creator")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建时间: {}",
                DateUtil.format(new Date(json.getLong("timestamp_micro")), NORM_DATETIME_FORMAT)));
        ;
        builder.append("\n");
        builder.append("- 相关链接:");
        builder.append("\n");
        builder.append(StrFormatter.format("https://www.tapd.cn/{}/prong/stories/view/{}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(newTagJson.getString("name"))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)
                .build();
    }

    public DingDingMessageDto getStoryChagerTextMessage(JSONObject json) {
        JSONObject oldTagJson = json.getJSONObject("old");
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("需求: {} 【状态变更】",
                StringUtils.nvlStr(newTagJson.getString("name"), oldTagJson.getString("name"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("状态: {}",
                StoryCommonEnums.StoryFlowEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("status"), oldTagJson.getString("status"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("优先级: {}",
                StoryCommonEnums.StoryPriorityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("priority"), oldTagJson.getString("priority"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("预计开始: {}",
                StringUtils.nvlStr(newTagJson.getString("begin"), oldTagJson.getString("begin"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("预计结束: {}",
                StringUtils.nvlStr(newTagJson.getString("due"), oldTagJson.getString("due"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("指派给: {}",
                StringUtils.nvlStr(newTagJson.getString("owner"), oldTagJson.getString("owner"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("操作时间: {}",
                StringUtils.nvlStr(newTagJson.getString("modified"), oldTagJson.getString("modified"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建人: {}",
                StringUtils.nvlStr(newTagJson.getString("creator"), oldTagJson.getString("creator"))));
        builder.append("\n");
        builder.append(StrFormatter.format("创建时间: {}",
                StringUtils.nvlStr(newTagJson.getString("created"), oldTagJson.getString("created"))

        ));

        builder.append("\n");
        builder.append("相关链接:");
        builder.append("\n");
        builder.append(StrFormatter.format("https://www.tapd.cn/{}/prong/stories/view/{}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(StringUtils.nvlStr(newTagJson.getString("name"), oldTagJson.getString("name")))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)
                .build();
    }

    public DingDingMessageDto getStoryChagerMdMessage(JSONObject json) {
        JSONObject oldTagJson = json.getJSONObject("old");
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("##### 需求: {} 【变更】",
                StringUtils.nvlStr(newTagJson.getString("name"), oldTagJson.getString("name"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 状态: {}",
                StoryCommonEnums.StoryFlowEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("status"), oldTagJson.getString("status"))
                )));
        builder.append("\n");
        builder.append(StrFormatter.format("- 优先级: <font color='#FF0000'>{}</font>",
                StoryCommonEnums.StoryPriorityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("priority"), oldTagJson.getString("priority"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("- 预计开始: {}",
                StringUtils.nvlStr(newTagJson.getString("begin"), oldTagJson.getString("begin"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 预计结束: {}",
                StringUtils.nvlStr(newTagJson.getString("due"), oldTagJson.getString("due"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 指派给：<font color='#FF0000'>{}</font>  ",
                StringUtils.nvlStr(newTagJson.getString("owner"), oldTagJson.getString("owner"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 操作时间: {}",
                StringUtils.nvlStr(newTagJson.getString("modified"), oldTagJson.getString("modified"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建人: <font color='#FF0000'>{}</font>",
                StringUtils.nvlStr(newTagJson.getString("creator"), oldTagJson.getString("creator"))));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建时间: {}",
                StringUtils.nvlStr(newTagJson.getString("created"), oldTagJson.getString("created"))
        ));
        ;
        builder.append("\n");
        builder.append("- 相关链接:");
        builder.append("\n");
        builder.append(StrFormatter.format("https://www.tapd.cn/{}/prong/stories/view/{}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(StringUtils.nvlStr(newTagJson.getString("name"), oldTagJson.getString("name")))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)
                .build();
    }
}
