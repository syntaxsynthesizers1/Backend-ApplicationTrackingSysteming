package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
