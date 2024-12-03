package com.example.demoapi.model.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramPayload {
    Integer chat_id;
    String text;
}
