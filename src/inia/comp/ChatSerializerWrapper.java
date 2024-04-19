package inia.comp;

import java.lang.reflect.Method;

import inia.McVersion;
import inia.ReflectUtils;

public class ChatSerializerWrapper {

    public static ChatBaseComponentWrapper deserialize(String text) {
        Class<?> HANDLE; 
        
        if(McVersion.CURRENT_VERSION.isNewerThan(McVersion.v1_8_R1))
            HANDLE = ReflectUtils.getNmsClass("IChatBaseComponent$ChatSerializer");
        else
            HANDLE = ReflectUtils.getNmsClass("ChatSerializer");

        try {
            Method deserialize = HANDLE.getMethod("a", String.class);
            Object handle = deserialize.invoke(null, text);

            return new ChatBaseComponentWrapper(handle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
