package util;

import websocket.Conversation;
import websocket.Staff;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class StaffDispatch {
    private final static PriorityBlockingQueue<Staff> staffQueue=new PriorityBlockingQueue<>(32);
    private final static ConcurrentHashMap<Integer,Staff> serveMap=new ConcurrentHashMap<>(256);
    private final static ConcurrentHashMap<Integer,Conversation> conversationMap=new ConcurrentHashMap<>(256);
    public void addStaff(int uid){
        staffQueue.add(new Staff(uid,0,0));
    }
    public void removeStaff(int uid){
        Staff want=null;
        for (Staff o:staffQueue) {
            if(o.getUid()==uid){
                want=o;
            }
        }
        if(want!=null){
            staffQueue.remove(want);
        }
        serveMap.entrySet().removeIf(item -> item.getValue().getUid() == uid);
    }
    public int matchStaff(Conversation conversation){
        Staff staff=staffQueue.poll();
        if(staff==null) {
            return 0;
        }else{
            staff.addConversation(conversation);
            staffQueue.add(staff);
            serveMap.put(conversation.getCid(), staff);
            conversationMap.put(conversation.getCid(),conversation);
            return staff.getUid();
        }
    }
    public void customerLeave(int cid){
        Staff staff=serveMap.remove(cid);
        Conversation conversation=conversationMap.get(cid);
        if(staff!=null) {
            staffQueue.remove(staff);
            staff.removeConversation(conversation);
            staffQueue.add(staff);
        }
    }
}
