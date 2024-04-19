package inia.packet.play_out;

import java.lang.reflect.Constructor;

import inia.ReflectUtils;

public class PacketPlayOutEntityDestroyWrapper extends PacketPlayOutWrapper {
    
    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutEntityDestroy");
    
    private int[] idArray;

    public PacketPlayOutEntityDestroyWrapper(int[] idArray) {
        this.setIdArray(idArray);
    }

    public int[] getIdArray() {
        return this.idArray;
    }

    public void setIdArray(int[] idArray) {
        this.idArray = idArray;
    }
    
    public Object getHandle() {
        if(HANDLE == null) return null;

        try {
            Constructor<?> packetConstructor = HANDLE.getConstructor(
                int[].class
            );
                
            return packetConstructor.newInstance(this.idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
