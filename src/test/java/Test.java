
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import websocket.Conversation;
import websocket.Staff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);
    public static void main(String[] args) {
        PriorityBlockingQueue<Staff> staffQueue=new PriorityBlockingQueue<>(32);
        ConcurrentHashMap<Conversation,Staff> serveMap=new ConcurrentHashMap<>(256);
        logger.info("tetetete");
    }
}
