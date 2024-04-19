package inia.packet.play_out;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import inia.McVersion;
import inia.comp.ChatMessageType;

public class PacketOutActionBarWrapper extends PacketPlayOutWrapper {

    private String text;
    private boolean resolveOnChat = true;

    public PacketOutActionBarWrapper(String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isResolveOnChat() {
        return this.resolveOnChat;
    }

    public void setResolveOnChat(boolean resolveOnChat) {
        this.resolveOnChat = resolveOnChat;
    }
 
    public Object getHandle() {
        JsonObject json = new JsonObject();
        json.add("text", new JsonPrimitive(this.getText()));
        String jsonString = json.toString();

        try {
            ChatMessageType msgType;

            if(McVersion.CURRENT_VERSION.isNewerThan(McVersion.v1_8_R1)) {
                msgType = ChatMessageType.ACTIONBAR;
            } else {
                if(this.isResolveOnChat()) {
                    msgType = ChatMessageType.MESSAGE;
                } else return null;
            }

            return PacketPlayOutChatWrapper.getChatHandle(jsonString, msgType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isAvailable() {
        return McVersion.CURRENT_VERSION.isNewerThan(McVersion.v1_8_R1);
    }
}
