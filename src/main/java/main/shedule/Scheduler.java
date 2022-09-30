package main.shedule;


import main.entity.Notification;
import main.entity.NotificationStatus;
import main.repsitory.NotificationRepository;
import main.repsitory.NotificationStatusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Scheduler {

    private final NotificationRepository notificationRepository;
    private final NotificationStatusRepository notificationStatusRepository;

    public Scheduler(NotificationRepository notificationRepository,
    NotificationStatusRepository notificationStatusRepository){
        this.notificationRepository = notificationRepository;
        this.notificationStatusRepository = notificationStatusRepository;

    }

    @Scheduled(cron = "59 59 23 * * *")
    public void updateNotifications(){
        System.out.println("Starting to update notifications");
        update();
        System.out.println("Finished");
    }

    private void update(){
        List<Notification> notificationList = notificationRepository.getAllBy();
        for(Notification notification: notificationList){
            if(notification.getStatus().equals("NOT_CONFIRMED")){
                Optional<NotificationStatus> optionalNotificationStatus
                        = notificationStatusRepository.
                        getNotificationStatusByNotificationId(notification.getId());
                if(optionalNotificationStatus.isPresent()){
                    NotificationStatus notificationStatus = optionalNotificationStatus.get();
                    notificationStatus.setIsRead(false);
                    notificationStatusRepository.save(notificationStatus);
                }
            }
        }
    }
}
