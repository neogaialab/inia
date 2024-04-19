package inia.ent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import inia.ReflectUtils;
import inia.packet.PacketWrapper;

public class PlayerWrapper extends EntityWrapper {

	private Class<?> PACKET = ReflectUtils.getNmsClass("Packet");

    public PlayerWrapper(Player bukkitPlayer) {
        super(PlayerWrapper.getHandle(bukkitPlayer));
    }

    private static Object getHandle(Player bukkitPlayer) {
		try {
			Class<?> bukkitPlayerClass = bukkitPlayer.getClass();
			Method getHandle = bukkitPlayerClass.getMethod("getHandle");
			
			return getHandle.invoke(bukkitPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

	/*
	public static int getPing(Player player) {
		CraftPlayer craftPlayer = ((CraftPlayer) player);

		if(craftPlayer != null) {
			EntityPlayer entityPlayer = craftPlayer.getHandle();
			
			if(entityPlayer != null) return entityPlayer.ping;
		}

		return 0;
	}
	*/

    public Object getPlayerConnection() {
        try {
			Object handle = super.getHandle();
			Class<?> handleClass = handle.getClass();
			Field playerConnection = handleClass.getField("playerConnection");
			return playerConnection.get(handle);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }

    public void sendPacketHandle(Object packet) {
        try {
			Object playerConnection = this.getPlayerConnection();
			Class<?> playerConnectionClass = playerConnection.getClass();
			Method sendPacket = playerConnectionClass.getMethod("sendPacket", PACKET);
			sendPacket.invoke(playerConnection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void sendPacket(PacketWrapper wPacket) {
		Object packetHandle = wPacket.getHandle();

		this.sendPacketHandle(packetHandle);
	}

    public int getPing() {
        

		return -1;
    }
}
