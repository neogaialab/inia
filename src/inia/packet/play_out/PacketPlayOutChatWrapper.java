package inia.packet.play_out;

import java.lang.reflect.Constructor;

import org.bukkit.ChatColor;

import inia.McVersion;
import inia.ReflectUtils;
import inia.comp.ChatBaseComponentWrapper;
import inia.comp.ChatComponentTextWrapper;
import inia.comp.ChatMessageType;
import inia.comp.ChatSerializerWrapper;

public class PacketPlayOutChatWrapper extends PacketPlayOutWrapper {
    
    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutChat");
    
    private static final Class<?> CHAT_BASE_COMPONENT = ReflectUtils.getNmsClass("IChatBaseComponent");
    private static final Class<?> CHAT_MESSAGE_TYPE = byte.class;

    private ChatBaseComponentWrapper chatBaseComponentWrapper;
    private ChatComponentTextWrapper chatComponentTextWrapper;
    private ChatMessageType chatMessageType;

    public PacketPlayOutChatWrapper(ChatBaseComponentWrapper chatBaseComponentWrapper, ChatMessageType chatMessageType) {
        this.setChatBaseComponentWrapper(chatBaseComponentWrapper);
        this.setChatMessageType(chatMessageType);
    }

    public PacketPlayOutChatWrapper(ChatComponentTextWrapper chatComponentTextWrapper, ChatMessageType chatMessageType) {
        this.setChatComponentTextWrapper(chatComponentTextWrapper);
        this.setChatMessageType(chatMessageType);
    }

    public ChatComponentTextWrapper getChatComponentTextWrapper() {
        return this.chatComponentTextWrapper;
    }

    public void setChatComponentTextWrapper(ChatComponentTextWrapper chatComponentTextWrapper) {
        this.chatComponentTextWrapper = chatComponentTextWrapper;
    }

    public ChatBaseComponentWrapper getChatBaseComponentWrapper() {
        return this.chatBaseComponentWrapper;
    }

    public void setChatBaseComponentWrapper(ChatBaseComponentWrapper chatBaseComponentWrapper) {
        this.chatBaseComponentWrapper = chatBaseComponentWrapper;
    }

    public ChatMessageType getChatMessageType() {
        return this.chatMessageType;
    }

    public void setChatMessageType(ChatMessageType chatMessageType) {
        this.chatMessageType = chatMessageType;
    }

    @Override
    public Object getHandle() {
        if(HANDLE == null) return null;

        try {
            Object contentComponentHandle = null;
            
            if(this.getChatBaseComponentWrapper() != null)
                contentComponentHandle = this.chatBaseComponentWrapper.getHandle();

            if(this.getChatComponentTextWrapper() != null)
                contentComponentHandle = this.chatComponentTextWrapper.getHandle();
            
            Constructor<?> packetConstructor = HANDLE.getConstructor(
                CHAT_BASE_COMPONENT,
                CHAT_MESSAGE_TYPE
            );
            Object packet = packetConstructor.newInstance(
                contentComponentHandle,
                chatMessageType.getData()
            );

            return packet;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getChatHandle(String jsonString, ChatMessageType msgType) {
        try {
            McVersion curV = McVersion.CURRENT_VERSION;

            PacketPlayOutChatWrapper wPacket;
            
            if(curV.isNewerThan(McVersion.v1_8_R1)) {
                if(curV.isNewerThan(McVersion.v1_9_R1)) {
                    jsonString = ChatColor.translateAlternateColorCodes((char) '&', (String) jsonString);
                    
                    ChatComponentTextWrapper wBaseComp = new ChatComponentTextWrapper(jsonString);
                    wPacket = new PacketPlayOutChatWrapper(wBaseComp, msgType);
                } else {
                    ChatBaseComponentWrapper wBaseComp = ChatSerializerWrapper.deserialize(jsonString);
                    wPacket = new PacketPlayOutChatWrapper(wBaseComp, msgType);
                }

                return wPacket.getHandle();
            }

            ChatBaseComponentWrapper wBaseComp = ChatSerializerWrapper.deserialize(jsonString);
            Object baseCompHandle = wBaseComp.getHandle();

            Constructor<?> packetConstructor = HANDLE.getConstructor(
                CHAT_BASE_COMPONENT,
                boolean.class
            );
            Object handle = packetConstructor.newInstance(
                baseCompHandle,
                true
            );

            return handle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
