package com.example.phshrobot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;

import com.alibaba.fastjson.JSONObject;
import com.example.phshrobot.constant.dingding.DingDingSendTypeConstant;
import com.example.phshrobot.constant.tapd.BugCommonEnums;
import com.example.phshrobot.constant.tapd.TaskCommonEnums;
import com.example.phshrobot.dto.DingDingMessageDto;
import com.example.phshrobot.dto.TapdEventParamsDto;
import com.example.phshrobot.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_FORMAT;
import static com.example.phshrobot.constant.tapd.TapdEventKeysConstant.*;

@Service
public class TapdBugEventService implements ITapdCommonService {

    @Override
    public DingDingMessageDto getMessageData(TapdEventParamsDto eventParamsDto) {
        DingDingMessageDto messageDto = null;
        String toKey = eventParamsDto.getEventKey() + eventParamsDto.getType();
        switch (toKey) {
            case EVENTS_TAG_BUG_CREATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
                messageDto = getBugCraeteTextMessage(eventParamsDto.getEvents());
                break;
            case EVENTS_TAG_BUG_CREATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
                messageDto = getBugCraeteMdMessage(eventParamsDto.getEvents());
                break;
            case EVENTS_TAG_BUG_STATUS_CHANGE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
            case EVENTS_TAG_BUG_UPDATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT:
                messageDto = getBugChangeTextMessage(eventParamsDto.getEvents());
                break;
            case EVENTS_TAG_BUG_UPDATE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
            case EVENTS_TAG_BUG_STATUS_CHANGE + DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD:
                messageDto = getBugChangeMdMessage(eventParamsDto.getEvents());
                break;
            default:
                break;
        }
        return messageDto;
    }


    private DingDingMessageDto getBugCraeteTextMessage(JSONObject json) {
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("BUG: {} ",
                newTagJson.getString("title")));
        builder.append("\n");
        builder.append(StrFormatter.format("状态: {}",
                "新增"));
        builder.append("\n");
        builder.append(StrFormatter.format("优先级: {}",
                BugCommonEnums.BugPriorityEnums.getKeyByValue(newTagJson.getString("priority"))));
        builder.append("\n");
        builder.append(StrFormatter.format("级别: {}",
                BugCommonEnums.BugSeverityEnums.getKeyByValue(newTagJson.getString("severity"))));
        builder.append("\n");
        builder.append(StrFormatter.format("所属模块: {}",
                newTagJson.getString("module")));
        builder.append("\n");
        builder.append(StrFormatter.format("指派给: {}",
                newTagJson.getString("current_owner")));
        builder.append("\n");
        builder.append(StrFormatter.format("创建人: {}",
                newTagJson.getString("reporter")));
        builder.append("\n");
        builder.append(StrFormatter.format("创建时间: {}",
                DateUtil.format(new Date(json.getLong("timestamp_micro")), NORM_DATETIME_FORMAT)));
        builder.append("\n");
        builder.append(StrFormatter.format(
                "相关链接: https://www.tapd.cn/{}/bugtrace/bugs/view?bug_id={}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(newTagJson.getString("title"))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)
                .build();

    }

    private DingDingMessageDto getBugCraeteMdMessage(JSONObject json) {
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("##### BUG: {} ",
                newTagJson.getString("title")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 状态: {}",
                "新增"));
        builder.append("\n");
        builder.append(StrFormatter.format("- 优先级: <font color='#FF0000'>{}</font>",
                BugCommonEnums.BugPriorityEnums.getKeyByValue(newTagJson.getString("priority"))));
        builder.append("\n");
        builder.append(StrFormatter.format("- 级别: {}",
                BugCommonEnums.BugSeverityEnums.getKeyByValue(newTagJson.getString("severity"))));
        builder.append("\n");
        builder.append(StrFormatter.format("- 所属模块: {}",
                newTagJson.getString("module")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 指派给: <font color='#FF0000'>{}</font>",
                newTagJson.getString("current_owner")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建人: <font color='#FF0000'>{}</font>",
                newTagJson.getString("reporter")));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建时间: {}",
                DateUtil.format(new Date(json.getLong("timestamp_micro")), NORM_DATETIME_FORMAT)));
        builder.append("\n");
        builder.append(StrFormatter.format(
                "- 相关链接: https://www.tapd.cn/{}/bugtrace/bugs/view?bug_id={}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(newTagJson.getString("title"))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)
                .build();

    }

    private DingDingMessageDto getBugChangeMdMessage(JSONObject json) {
        JSONObject oldTagJson = json.getJSONObject("old");
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("##### BUG: {} 【变更】 ",
                StringUtils.nvlStr(newTagJson.getString("title"),
                        oldTagJson.getString("title"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 状态: {}",
                BugCommonEnums.BugBugFlowEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("status"),
                                oldTagJson.getString("status"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("- 优先级: <font color='#FF0000'>{}</font>",
                BugCommonEnums.BugPriorityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("priority"),
                                oldTagJson.getString("priority"))


                )));
        builder.append("\n");
        builder.append(StrFormatter.format("- 级别: {}",
                BugCommonEnums.BugSeverityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("severity"),
                                oldTagJson.getString("severity"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("- 所属模块: {}",
                StringUtils.nvlStr(newTagJson.getString("module"),
                        oldTagJson.getString("module"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 指派给: <font color='#FF0000'>{}</font>",
                StringUtils.nvlStr(newTagJson.getString("current_owner"),
                        oldTagJson.getString("current_owner"))

        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建人: <font color='#FF0000'>{}</font>",
                StringUtils.nvlStr(newTagJson.getString("reporter"),
                        oldTagJson.getString("reporter"))

        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 创建时间: {}",
                StringUtils.nvlStr(newTagJson.getString("created"),
                        oldTagJson.getString("created"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 操作人员: {}",
                StringUtils.nvlStr(newTagJson.getString("reporter"),
                        oldTagJson.getString("reporter"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("- 操作时间: {}",
                StringUtils.nvlStr(newTagJson.getString("modified"),
                        oldTagJson.getString("modified"))

        ));
        builder.append("\n");
        builder.append(StrFormatter.format(
                "- 相关链接: https://www.tapd.cn/{}/bugtrace/bugs/view?bug_id={}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(StringUtils.nvlStr(newTagJson.getString("title"),
                        oldTagJson.getString("title")))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_MD)
                .build();
    }

    private DingDingMessageDto getBugChangeTextMessage(JSONObject json) {
        JSONObject oldTagJson = json.getJSONObject("old");
        JSONObject newTagJson = json.getJSONObject("new");
        StringBuilder builder = new StringBuilder();
        builder.append(StrFormatter.format("BUG: {} 【变更】 ",
                StringUtils.nvlStr(newTagJson.getString("title"),
                        oldTagJson.getString("title"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("状态: {}",
                BugCommonEnums.BugBugFlowEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("status"),
                                oldTagJson.getString("status"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("优先级: {}",
                BugCommonEnums.BugPriorityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("priority"),
                                oldTagJson.getString("priority"))


                )));
        builder.append("\n");
        builder.append(StrFormatter.format("级别: {}",
                BugCommonEnums.BugSeverityEnums.getKeyByValue(
                        StringUtils.nvlStr(newTagJson.getString("severity"),
                                oldTagJson.getString("severity"))

                )));
        builder.append("\n");
        builder.append(StrFormatter.format("所属模块: {}",
                StringUtils.nvlStr(newTagJson.getString("module"),
                        oldTagJson.getString("module"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("指派给: {}",
                StringUtils.nvlStr(newTagJson.getString("current_owner"),
                        oldTagJson.getString("current_owner"))

        ));
        builder.append("\n");
        builder.append(StrFormatter.format("创建人: {}",
                StringUtils.nvlStr(newTagJson.getString("reporter"),
                        oldTagJson.getString("reporter"))

        ));
        builder.append("\n");



        builder.append(StrFormatter.format("创建时间: {}",
                StringUtils.nvlStr(newTagJson.getString("created"),
                        oldTagJson.getString("created"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("操作人员: {}",
                StringUtils.nvlStr(newTagJson.getString("reporter"),
                        oldTagJson.getString("reporter"))
        ));
        builder.append("\n");
        builder.append(StrFormatter.format("操作时间: {}",
                StringUtils.nvlStr(newTagJson.getString("modified"),
                        oldTagJson.getString("modified"))

        ));
        builder.append("\n");
        builder.append(StrFormatter.format(
                "相关链接: https://www.tapd.cn/{}/bugtrace/bugs/view?bug_id={}", json.getString("workspace_id"), json.getString("id")));
        return DingDingMessageDto.builder()
                .text(builder.toString())
                .title(StringUtils.nvlStr(newTagJson.getString("title"),
                        oldTagJson.getString("title")))
                .type(DingDingSendTypeConstant.MESSAGE_SEND_TYPE_TEXT)
                .build();
    }
}
