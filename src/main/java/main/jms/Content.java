package main.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content implements Serializable {
        private Long id;
        private Long userSenderId;
        private String status;
        private Long pageId;
        private Long changeId;
        private Boolean isRead;
}
