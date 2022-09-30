package main.jms;

import lombok.extern.slf4j.Slf4j;
import main.entity.Notification;
import main.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


@Service
@Slf4j
public class JmsReceiver {

    private final NotificationService notificationService;

    @Autowired
    public JmsReceiver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "exampleQueue")
    public void getMessage(@Payload byte[] content) throws IOException, ClassNotFoundException {
        log.info(deserialize(content).toString());
        Notification notification = (Notification) deserialize(content);
        notificationService.saveNotification(notification);

    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
  }
}
