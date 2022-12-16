package services.api;

import dto.MessageDTO;

import java.util.List;

public interface IMessageService {

    void send(MessageDTO message);
    List<MessageDTO> get(String recipient);
}
