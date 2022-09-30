package main.repsitory;

import main.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> getNotificationByUserIdAndUserSenderIdAndPageIdAndChangeId
            (Long userId, Long senderId, Long pageId, Long ChangeId);

    List<Notification> getAllBy();

}

