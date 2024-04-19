package inia.ent;

import java.lang.reflect.Method;

import inia.packet.PacketUtils;

public class EntityLivingWrapper extends EntityWrapper {

    public EntityLivingWrapper(Object handle) {
        super(handle);
    }
    
    public void setLocation(double x, double y, double z, float yaw, float pitch) {
        try {
            Object handle = super.getHandle();
            Class<?> handleClass = handle.getClass();
            Method setter = handleClass.getMethod(
                "setLocation",
                double.class,
                double.class,
                double.class,
                float.class,
                float.class
            );
            
            setter.invoke(handle, x, y, z, yaw, pitch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInvisible(boolean argument) {
        Object handle = super.getHandle();
        PacketUtils.setBoolean(handle, "setInvisible", argument);
    }

    public void setCustomNameVisible(boolean argument) {
        Object handle = super.getHandle();
        PacketUtils.setBoolean(handle, "setCustomNameVisible", argument);
    }

    public void setCustomName(String argument) {
        Object handle = super.getHandle();
        PacketUtils.setString(handle, "setCustomName", argument);
    }

    public void setSmall(boolean argument) {
        Object handle = super.getHandle();
        PacketUtils.setBoolean(handle, "setSmall", argument);
    }

    public void setGravity(boolean argument) {
        Object handle = super.getHandle();
        PacketUtils.setBoolean(handle, "setGravity", argument);
    }
}
