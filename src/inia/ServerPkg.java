package inia;

import org.bukkit.Bukkit;

public enum ServerPkg {

    NM("net.minecraft." + getServerVersion()),
    NMS("net.minecraft.server." + getServerVersion()),
    OBC("org.bukkit.craftbukkit." + getServerVersion());

    private final String path;
    
    ServerPkg(String path) {
        this.path = path;
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    @Override
    public String toString() {
        return this.path;
    }

    public Class<?> getClass(String className) {
        try {
            return Class.forName(this.toString() + "." + className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public boolean isClassAvailable(String className) {
        return this.getClass(className) != null;
    }
}