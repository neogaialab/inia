package inia.packet.play_out;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import inia.McVersion;
import inia.ReflectUtils;
import inia.comp.ChatBaseComponentWrapper;
import inia.comp.ChatMessageType;
import inia.comp.TitleMode;

public class PacketPlayOutTitleWrapper extends PacketPlayOutWrapper {
    
    private static final Class<?> CHAT_BASE_COMPONENT = ReflectUtils.getNmsClass("IChatBaseComponent");
    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutTitle");
	private static final Class<?> PACKET_PLAY_OUT_TITLE_0 = ReflectUtils.getNmsSubclass(HANDLE, 0);
    
    private TitleMode mode;
    private ChatBaseComponentWrapper chatBaseComponentWrapper;
    private int fadeInTime, showTime, fadeOutTime;
    
    private String text;

    private boolean resolveOnChat = true;

    public PacketPlayOutTitleWrapper(TitleMode mode, ChatBaseComponentWrapper chatBaseComponentWrapper, int fadeInTime, int showTime, int fadeOutTime, String text) {
        this.setMode(mode);
        this.setChatBaseComponentWrapper(chatBaseComponentWrapper);
        this.setFadeInTime(fadeInTime);
        this.setShowTime(showTime);
        this.setFadeOutTime(fadeOutTime);
        this.setText(text);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TitleMode getMode() {
        return this.mode;
    }

    public void setMode(TitleMode mode) {
        this.mode = mode;
    }

    public ChatBaseComponentWrapper getChatBaseComponentWrapper() {
        return this.chatBaseComponentWrapper;
    }

    public void setChatBaseComponentWrapper(ChatBaseComponentWrapper chatBaseComponentWrapper) {
        this.chatBaseComponentWrapper = chatBaseComponentWrapper;
    }

    public int getFadeInTime() {
        return this.fadeInTime;
    }

    public void setFadeInTime(int fadeInTime) {
        this.fadeInTime = fadeInTime;
    }

    public int getShowTime() {
        return this.showTime;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public int getFadeOutTime() {
        return this.fadeOutTime;
    }

    public void setFadeOutTime(int fadeOutTime) {
        this.fadeOutTime = fadeOutTime;
    }

    public boolean isResolveOnChat() {
        return this.resolveOnChat;
    }

    public void setResolveOnChat(boolean resolveOnChat) {
        this.resolveOnChat = resolveOnChat;
    }

    @Override
    public Object getHandle() {
        System.out.println("----- a");

        JsonObject json = new JsonObject();
        json.add("text", new JsonPrimitive(this.getText()));
        String jsonString = json.toString();

        try {
            if(McVersion.CURRENT_VERSION.isNewerThan(McVersion.v1_8_R1)) {
                String titleModeFieldName = mode.getName();
                Field titleMode = PACKET_PLAY_OUT_TITLE_0.getField(titleModeFieldName);

                Object contentComponentHandle = this.chatBaseComponentWrapper.getHandle();
                
                Constructor<?> packetConstructor = HANDLE.getConstructor(
                    PACKET_PLAY_OUT_TITLE_0,
                    CHAT_BASE_COMPONENT,
                    int.class,
                    int.class,
                    int.class
                );
                
                
                Object handle = packetConstructor.newInstance(
                    titleMode.get(null),
                    contentComponentHandle,
                    fadeInTime,
                    showTime, 
                    fadeOutTime
                );

                System.out.println("--- packeting title");

                return handle;
            }

            if(!this.isResolveOnChat()) return null;

            return PacketPlayOutChatWrapper.getChatHandle(jsonString, ChatMessageType.MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isAvailable() {
        return McVersion.CURRENT_VERSION.isNewerThan(McVersion.v1_8_R1);
    }
}
