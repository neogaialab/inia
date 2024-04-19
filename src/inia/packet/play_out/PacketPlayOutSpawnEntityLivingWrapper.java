package inia.packet.play_out;

import java.lang.reflect.Constructor;

import inia.ReflectUtils;
import inia.ent.EntityLivingWrapper;

public class PacketPlayOutSpawnEntityLivingWrapper extends PacketPlayOutWrapper {
    
    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutSpawnEntityLiving");

    private static final Class<?> ENTITY_LIVING = ReflectUtils.getNmsClass("EntityLiving");

    private EntityLivingWrapper entityLiving;

    public PacketPlayOutSpawnEntityLivingWrapper(EntityLivingWrapper entityLiving) {
        this.setEntityLiving(entityLiving);
    }

    public EntityLivingWrapper getEntityLiving() {
        return this.entityLiving;
    }

    public void setEntityLiving(EntityLivingWrapper entityLiving) {
        this.entityLiving = entityLiving;
    }
    
    public Object getHandle() {
        if(HANDLE == null) return null;
        
        try {
            Object entHandle = this.entityLiving.getHandle();
            
            Constructor<?> packetConstructor = HANDLE.getConstructor(
                ENTITY_LIVING
            );
                
            return packetConstructor.newInstance(entHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
