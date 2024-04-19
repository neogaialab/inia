# Inia

Inia is a framework for cross-version plugin support in Minecraft servers.

## Features

* Packets
    * Playout
        * Chat
        * EntityDestroy
        * EntityTeleport
        * PlayerlistHeaderFooter
        * SpawnEntityLiving
        * Title
    * Handshaking
    * Login
    * Playin
    * Status
* Entities
    * ArmorStand
    * EntityLiving
    * Entity
    * Player
* Components
    * ChatBaseComponent
    * ChatSerializer
    * PlayerlistHeaderFooter
    * Materials: 
    * Sounds, supports before and above 1.9 sound names
* Packet Gadgets
    * Actionbar
* Utils
    * Reflection, Server Package
    * Version Utils
    * Packet Utils

## Solutions

### Method 1: importing package

Relative short code, but version depended and repetive code.

```java
import v1_8;

//...

int ping = ((CraftPlayer) player).getHandle().ping;
```

### Method 2: using Reflection

Version independed, but relative giant and repetive code.

```java
try {
    String bukkitversion = Bukkit.getServer()
        .getClass()
        .getPackage()
        .getName()
        .substring(23);
    Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
        + bukkitversion + ".entity.CraftPlayer");
    Object handle = craftPlayer.getMethod("getHandle")
        .invoke(player);
    Integer ping = (Integer) handle.getClass()
        .getDeclaredField("ping")
        .get(handle);
    
    return ping.intValue();
} catch (ClassNotFoundException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException
        | NoSuchFieldException e) {
    return -1;
}
```

### Method 3: using interface and importing respectively to implementation 

Repetitive code.

### Method 4: using library Inia

Short code and version independed.

```java
import inia.ent.PlayerWrapper;

PlayerWrapper wPlayer = new PlayerWrapper(player);
int ping = wPlayer.getPing();
```

## Minecraft Version Support

| Server Version | Client Version | Features Supported | Features Not Supported
| - | - | - | -
| 1.7.10 | " | mat | holo (natively), title (natively)
| 1.8.4 | 1.8.9 | message, actionbar, holo, title | 
| 1.9.4 | " | actionbar, holo, mat |
| 1.10.2 | " | actionbar, mat |
| 1.11.2 | " | actionbar, mat |
| 1.12.2 | " | mat | actionbar
| 1.13.2 | " | not-tested |
| 1.14.4 | " | not-tested |
| 1.15.2 | " | not-tested |
| 1.16.5 | " | not-tested |
| 1.17.1 | " | not-tested |
| 1.18.2 | " | mat | 

## Inspired by

* Sounds: https://gist.github.com/MrBlobman/64db96857209d93598300ace9d2b02d0
* Materials: https://www.spigotmc.org/wiki/cc-sounds-list/, https://github.com/LoneDev6/Material-support/blob/master/Mat.java

* https://github.com/TozyMC/treflections/tree/main/src/main/java/xyz/tozymc/minecraft
* https://www.spigotmc.org/threads/no-such-method.355323/?__cf_chl_tk=0QiCxvpnzeEkTdE6HzsUf4c7iD6kzLz4_85Fne2iM6Q-1654273645-0-gaNycGzNCGU
* https://bukkit.org/threads/multi-version-plugin.421416/

* https://getbukkit.org/download/spigot
