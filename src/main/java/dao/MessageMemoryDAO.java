package dao;

import dao.api.IMessageDAO;
import dto.MessageDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return Collections.unmodifiableList(this.userMessages.get(recipient));
    }

    public void add(MessageDTO message) {
        this.userMessages.get(message.getRecipient())
                .add(message);
    }
}
