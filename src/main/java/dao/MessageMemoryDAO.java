package dao;

import dto.MessageDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageMemoryDAO {

    private final Map<String, List<MessageDTO>> userMessages;

    public MessageMemoryDAO() {
        userMessages = new HashMap<>();
    }

    public List<MessageDTO> getAll() {
        List<MessageDTO> allMessages = userMessages
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());

        return allMessages;
    }
}
