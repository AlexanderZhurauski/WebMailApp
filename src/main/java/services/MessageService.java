package services;

import dao.api.IMessageDAO;
import dto.MessageDTO;
import dto.MessageRecipientDTO;
import entities.MessageEntity;
import services.api.IMessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private IMessageDAO dao;

    public MessageService(IMessageDAO dao) {
        this.dao = dao;
    }

    @Override
    public void send(MessageDTO message) {
        String sender = message.getSender();
        String recipient = message.getRecipient();
        String text = message.getText();
        LocalDateTime timeNow = LocalDateTime.now();

        dao.add(new MessageEntity(text, sender, recipient, timeNow));
    }

    @Override
    public List<MessageRecipientDTO> get(String recipient) {
        List<MessageRecipientDTO> userMessages = dao.get(recipient)
                .stream()
                .map(x -> new MessageRecipientDTO(x.getText(), x.getSender(),
                        x.getSendTime()))
                .collect(Collectors.toList());

        return userMessages;
    }
}
