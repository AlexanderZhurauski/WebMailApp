package dao.api;

import entities.MessageEntity;

import java.util.List;

public interface IMessageDAO {

    List<MessageEntity> getAll();
    List<MessageEntity> get(String recipient);
    void add(MessageEntity message);
 }
