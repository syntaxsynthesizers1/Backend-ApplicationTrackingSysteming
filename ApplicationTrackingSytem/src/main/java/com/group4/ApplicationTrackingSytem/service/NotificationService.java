package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.request.NotificationRequest;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;

public interface NotificationService {
    Response sendNotification (NotificationRequest noficationRequest);

}
