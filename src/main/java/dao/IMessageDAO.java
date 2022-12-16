package dao;

import dto.MessageDTO;

import java.util.List;

public interface IMessageDAO {

    List<MessageDTO> getAll();
    List<MessageDTO> get(String recipient);
    void add(MessageDTO message);
 }
