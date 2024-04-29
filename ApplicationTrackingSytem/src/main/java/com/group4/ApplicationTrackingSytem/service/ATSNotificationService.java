package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.request.NotificationRequest;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;
import com.group4.ApplicationTrackingSytem.mail.ATSEmailService;
import com.group4.ApplicationTrackingSytem.mail.EmailRequest;
import com.group4.ApplicationTrackingSytem.model.Notification;
import com.group4.ApplicationTrackingSytem.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ATSNotificationService implements NotificationService {
//    private final ATSEmailService atsEmailService;
    private final NotificationRepository notificationRepository;

    @Override
    public Response sendNotification(NotificationRequest noficationRequest) {
        Notification notification = new Notification();
        notification.setContent(noficationRequest.getContent());
        notification.setTimestamp(LocalDateTime.now());
        notification.setContent(noficationRequest.getContent());
        notificationRepository.save(notification);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(noficationRequest.getRecipientEmail());
        emailRequest.setBody(noficationRequest.getContent());
        return new Response("Notification sent successfully");

    }
}
