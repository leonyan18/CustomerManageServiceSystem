package service;

import dto.MessageDTO;
import com.alibaba.fastjson.JSONArray;
import org.springframework.data.domain.Pageable;
import websocket.Msg;

import java.util.List;

public interface MessageService {
    void handleMessage(Msg msg);
    long count(int cid);
    List<MessageDTO> findChatRecord(int cid, Pageable pageable);
    List<MessageDTO> findChatRecord(int cid);
    JSONArray matchAnswer(String problem);
}
