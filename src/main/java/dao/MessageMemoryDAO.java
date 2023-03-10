package dao;

import dao.api.IMessageDAO;
import entities.MessageEntity;

import java.util.*;
import java.util.stream.Collectors;

public class MessageMemoryDAO implements IMessageDAO {

    private final Map<String, List<MessageEntity>> userMessages;

    public MessageMemoryDAO() {
        this.userMessages = new HashMap<>();
    }

    @Override
    public List<MessageEntity> getAll() {
        List<MessageEntity> allMessages = this.userMessages
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());

        return allMessages;
    }

    @Override
    public List<MessageEntity> get(String recipient) {
        return this.userMessages.get(recipient);
    }

    @Override
    public void add(MessageEntity message) {
        List<MessageEntity> userInbox = this.userMessages.get(message.
                getRecipient());
        if (userInbox == null) {
            userInbox = new ArrayList<>();
            this.userMessages.put(message.getRecipient(), userInbox);
        }
        userInbox.add(message);
    }

    @Override
    public int getCount() {
        return userMessages.size();
    }
}
