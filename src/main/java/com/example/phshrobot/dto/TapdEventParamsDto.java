package com.example.phshrobot.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TapdEventParamsDto {

    private JSONObject events;
    private String type;
    private String eventKey;
    private JSONObject actions;
}
