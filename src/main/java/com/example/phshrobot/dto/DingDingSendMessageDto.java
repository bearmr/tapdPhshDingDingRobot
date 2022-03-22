package com.example.phshrobot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DingDingSendMessageDto {

    private String accessToken;
    private String secret;
    private DingDingMessageDto messageDto;
}
