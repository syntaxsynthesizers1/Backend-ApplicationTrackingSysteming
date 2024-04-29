package com.group4.ApplicationTrackingSytem.dto.request;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String recipientEmail;
    private String content;
}
