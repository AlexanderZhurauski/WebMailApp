package dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private final String text;
    private final String sender;
    private final String recipient;
    private final LocalDateTime sendTime;

    public MessageDTO(String text, String sender,
                      String recipient, LocalDateTime sendTime) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.sendTime = sendTime;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }
}
