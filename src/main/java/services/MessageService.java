package services;

import dao.api.IMessageDAO;
import dto.MessageDTO;
import services.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {

    private IMessageDAO dao;

    public MessageService(IMessageDAO dao) {
        this.dao = dao;
    }

    @Override
    public void send(MessageDTO message) {
        //TODO
    }

    @Override
    public List<MessageDTO> get(String recipient) {
        return dao.get(recipient);
    }
}
