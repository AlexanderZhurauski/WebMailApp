package services;

import dao.api.IMessageDAO;
import dao.api.IUserDAO;
import dto.MessageDTO;
import dto.MessageRecipientDTO;
import entities.MessageEntity;
import services.api.IMessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private IMessageDAO messageDAO;
    private IUserDAO userDAO;

    public MessageService(IMessageDAO messageDAO, IUserDAO userDAO) {
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void send(MessageDTO message) {
        String sender = message.getSender();
        String recipient = message.getRecipient();
        if (sender.equals(recipient)) {
            throw new IllegalArgumentException("You can't send a" +
                    " message to yourself!");
        }
        if(!this.userDAO.exist(recipient)) {
            throw new IllegalArgumentException("The the specified recipient" +
                    " doesn't exist!");
        }
        String text = message.getText();
        LocalDateTime timeNow = LocalDateTime.now();

        this.messageDAO.add(new MessageEntity(text, sender, recipient, timeNow));
    }

    @Override
    public List<MessageRecipientDTO> get(String recipient) {
        List<MessageEntity> messageEntities = this.messageDAO.get(recipient);
        if (messageEntities == null) {
            messageEntities = new ArrayList<>();
        }
        List<MessageRecipientDTO> userMessages = messageEntities
                .stream()
                .map(x -> new MessageRecipientDTO(x.getText(), x.getSender(),
                        x.getSendTime()))
                .collect(Collectors.toList());

        return userMessages;
    }
}
