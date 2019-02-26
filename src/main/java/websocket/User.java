package websocket;

import java.security.Principal;

public final class User implements Principal {

    private final String name;
    private final Integer conversationId;

    public User(String name,Integer conversationId) {
        this.name = name;
        this.conversationId=conversationId;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", conversationId=" + conversationId +
                '}';
    }
}