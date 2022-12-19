package services.api;

import dto.MessageDTO;
import dto.MessageRecipientDTO;

import java.util.List;

public interface IMessageService {

    void send(MessageDTO message);
    List<MessageRecipientDTO> get(String recipient);
}
