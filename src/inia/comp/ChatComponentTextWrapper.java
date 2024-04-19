package inia.comp;

import inia.ReflectUtils;

public class ChatComponentTextWrapper {
    
    private static final Class<?> CHAT_COMPONENT_TEXT = ReflectUtils.getNmsClass("ChatComponentText");

    private String json;

    public ChatComponentTextWrapper(String json) {
        this.setJson(json);
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Object getHandle() {
        try {
            Object chatComponentText = CHAT_COMPONENT_TEXT
                .getConstructor(String.class)
                .newInstance(json);

            return chatComponentText;
        } catch (Exception e) {
            return null;
        }
    }
}
