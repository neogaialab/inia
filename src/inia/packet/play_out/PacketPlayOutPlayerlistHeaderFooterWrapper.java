package inia.packet.play_out;

import java.lang.reflect.Constructor;

import inia.ReflectUtils;
import inia.comp.PlayerlistHeaderFooterWrapper;
import inia.packet.PacketUtils;

public class PacketPlayOutPlayerlistHeaderFooterWrapper extends PacketPlayOutWrapper {

    private static final Class<?> HANDLE = ReflectUtils.getNmsClass("PacketPlayOutPlayerListHeaderFooter");

    private PlayerlistHeaderFooterWrapper playerlistHeaderFooterWrapper;

    public PacketPlayOutPlayerlistHeaderFooterWrapper(PlayerlistHeaderFooterWrapper playerlistHeaderFooterWrapper) {
        this.setPlayerlistHeaderFooterWrapper(playerlistHeaderFooterWrapper);
    }

    public PlayerlistHeaderFooterWrapper getPlayerlistHeaderFooterWrapper() {
        return this.playerlistHeaderFooterWrapper;
    }

    public void setPlayerlistHeaderFooterWrapper(PlayerlistHeaderFooterWrapper playerlistHeaderFooterWrapper) {
        this.playerlistHeaderFooterWrapper = playerlistHeaderFooterWrapper;
    }

    @Override
    public Object getHandle() {
        if(HANDLE == null) return null;

        try {
            Object headerComponentHandle = this.playerlistHeaderFooterWrapper.getHeaderComponentWrapper().getHandle();
            Object footerComponentHandle = this.playerlistHeaderFooterWrapper.getFooterComponentWrapper().getHandle();
            
            Constructor<?> packetConstructor = HANDLE.getConstructor();
            
            Object handle = packetConstructor.newInstance();
            PacketUtils.setObject(handle, "a", headerComponentHandle);
            PacketUtils.setObject(handle, "b", footerComponentHandle);
            
            return handle;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
