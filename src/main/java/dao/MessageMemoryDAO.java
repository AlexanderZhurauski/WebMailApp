package dao;

import dao.api.IMessageDAO;
import dto.MessageDTO;

import java.util.*;
import java.util.stream.Collectors;

public class MessageMemoryDAO implements IMessageDAO {

    private final Map<String, List<MessageDTO>> userMessages;

    public MessageMemoryDAO() {
        this.userMessages = new HashMap<>();
    }

    public List<MessageDTO> getAll() {
        List<MessageDTO> allMessages = this.userMessages
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());

        return allMessages;
    }

    public List<MessageDTO> get(String recipient) {
        return this.userMessages.get(recipient);
    }

    public void add(MessageDTO message) {
        List<MessageDTO> userInbox = this.userMessages.get(message.
                getRecipient());
        if (userInbox == null) {
            userInbox = new ArrayList<>();
            this.userMessages.put(message.getRecipient(), userInbox);
        }
        userInbox.add(message);
    }
}
