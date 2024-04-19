package inia.packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import inia.ent.PlayerWrapper;

public class PacketUtils {
	
    /*
    public static Class<?> getNmsClass(String nmsClass) {
        nmsClass = "net.minecraft.server.%s." + nmsClass;
        
        String version = null;
        Pattern pat = Pattern.compile("net\\.minecraft\\.(?:server)?\\.(v(?:\\d_)+R\\d)");

        for(Package p : Package.getPackages()) {
            String name = p.getName();
            Matcher m = pat.matcher(name);
            if(m.matches()) version = m.group(1);
        }

        if(version == null) return null;

        String className = String.format(nmsClass, version); 

        try {
            System.out.println("NMS Class Name1: " + className);

			return Class.forName(className);
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
            return null;
        }
    }
    */

    
	public static Class<?> getNmsClass(String name) {
		try {
			Server server = Bukkit.getServer();
			Class<?> serverClass = server.getClass();
            Package serverPkg = serverClass.getPackage();
            String serverPkgName = serverPkg.getName();
			String nmsVersion = serverPkgName.split("\\.")[3];

            String nmsPkg = "net.minecraft.server";
            String nmsClassName = nmsPkg + "." + nmsVersion + "." + name;
            //nmsClassName = nmsPkg + "." + name;

            System.out.println("NMS Class Name: " + nmsClassName);

			return Class.forName(nmsClassName);
		} catch (ClassNotFoundException exception) {
			return null;
        }
	}
    
    public static Class<?> getNmsSubclass(Class<?> nmsClass, int index) {
        try {
            Class<?> subclass = nmsClass.getDeclaredClasses()[index];
            return subclass;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

	public static void sendPacketToOnlinePlayers(Object packet) {
		for(Player player: Bukkit.getOnlinePlayers()) {
			PlayerWrapper wPlayer = new PlayerWrapper(player);
			wPlayer.sendPacketHandle(packet);
		}
    }

    public static void setBoolean(Object instance, String methodName, boolean argument) {
        try {
			Class<?> instanceClass = instance.getClass();
            Method setter = instanceClass.getMethod(
                methodName,
                boolean.class
            );
            
            setter.invoke(instance, argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setString(Object instance, String methodName, String argument) {
        try {
			Class<?> instanceClass = instance.getClass();
            Method setter = instanceClass.getMethod(
                methodName,
                String.class
            );
            
            setter.invoke(instance, argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void setObject(boolean declared, Class<?> instanceClass, Object instance, String fieldName, Object fieldValue) {
        try {
            Field field = null;
            
            if(!declared)
                field = instanceClass.getField(fieldName);
            else
                field = instanceClass.getDeclaredField(fieldName);

            boolean accessibilityBackup = field.isAccessible();
            field.setAccessible(true);

            field.set(instance, fieldValue);
            
            field.setAccessible(accessibilityBackup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setObject(Object instance, String fieldName, Object fieldValue) {
        Class<?> instanceClass = instance.getClass();

        PacketUtils.setObject(false, instanceClass, instance, fieldName, fieldValue);
    }
}