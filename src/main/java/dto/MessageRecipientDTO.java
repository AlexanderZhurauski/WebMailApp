package dto;

import java.time.LocalDateTime;

public class MessageRecipientDTO {

    private final String text;
    private final String sender;
    private final LocalDateTime time;

    public MessageRecipientDTO(String text, String sender, LocalDateTime time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
