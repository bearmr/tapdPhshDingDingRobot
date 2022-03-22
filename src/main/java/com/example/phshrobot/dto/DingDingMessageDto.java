package com.example.phshrobot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class DingDingMessageDto {
    /**
     * 返回的文本类容
     */
    private String text;
    /**
     * 文本 text mackdow md
     */
    private String type;
    /**
     * 标题
     */
    private String title;

}
