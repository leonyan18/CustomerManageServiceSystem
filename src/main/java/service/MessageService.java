package service;

import websocket.Msg;

public interface MessageService {
    void sendMsgTo(Msg msg);
}
