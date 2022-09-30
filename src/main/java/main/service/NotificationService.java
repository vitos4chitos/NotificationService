package main.service;

import main.entity.Notification;
import main.entity.NotificationStatus;
import main.repsitory.NotificationRepository;
import main.repsitory.NotificationStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationStatusRepository notificationStatusRepository;

    public NotificationService(NotificationRepository notificationRepository,
                               NotificationStatusRepository notificationStatusRepository){
        this.notificationRepository = notificationRepository;
        this.notificationStatusRepository = notificationStatusRepository;
    }


    public void saveNotification(Notification notification){
        notificationRepository.save(notification);
        NotificationStatus notificationStatus = new NotificationStatus();
        notificationStatus.setIsRead(false);
        notificationStatus.setNotificationId(notification.getId());
        notificationStatusRepository.save(notificationStatus);
    }


}
