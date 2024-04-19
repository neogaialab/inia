package inia.packet.play_out;

import java.lang.reflect.Constructor;

import inia.ReflectUtils;
import inia.ent.EntityWrapper;

public class PacketPlayOutEntityTeleportWrapper extends PacketPlayOutWrapper {
    
    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutEntityTeleport");
    
    private static final Class<?> ENTITY = ReflectUtils.getNmsClass("Entity");

    private EntityWrapper entity;

    public PacketPlayOutEntityTeleportWrapper(EntityWrapper entity) {
        this.setEntity(entity);
    }

    public EntityWrapper getEntity() {
        return this.entity;
    }

    public void setEntity(EntityWrapper entity) {
        this.entity = entity;
    }

    @Override
    public Object getHandle() {
        if(HANDLE == null) return null;

        try {
            Object entHandle = this.entity.getHandle();

            Constructor<?> packetConstructor = HANDLE.getConstructor(
                ENTITY
            );
            
            return packetConstructor.newInstance(entHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
