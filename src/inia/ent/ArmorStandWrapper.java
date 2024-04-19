package inia.ent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.World;

import inia.ReflectUtils;

public class ArmorStandWrapper extends EntityLivingWrapper {

    private final static Class<?> WORLD = ReflectUtils.getNmsClass("World");
    private final static Class<?> HANDLE = ReflectUtils.getNmsClass("EntityArmorStand");

    public ArmorStandWrapper(World world) {
        super(ArmorStandWrapper.getHandle(world));
    }

    public static boolean isAvailable() {
        return ArmorStandWrapper.HANDLE != null;
    }

    private static Object getHandle(World world) {
        if(HANDLE == null) return null;

        try {
            Class<?> worldClass = world.getClass();
            Method getHandle = worldClass.getMethod("getHandle");
            Object worldServer = getHandle.invoke(world);

            Constructor<?> handleConstructor = HANDLE.getConstructor(
                WORLD
            );
            
            return handleConstructor.newInstance(worldServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
