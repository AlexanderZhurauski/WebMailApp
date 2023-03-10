package dto;

public class MessageDTO {

    private final String text;
    private final String sender;
    private final String recipient;

    public MessageDTO(String text, String sender,
                         String recipient) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
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
}
