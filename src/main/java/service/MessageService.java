package service;

import DTO.MessageDTO;
import org.springframework.data.domain.Pageable;
import websocket.Msg;

import java.util.List;

public interface MessageService {
    void handleMessage(Msg msg);
    long count(int cid);
    List<MessageDTO> findChatRecord(int cid, Pageable pageable);
}
