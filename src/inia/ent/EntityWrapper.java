package inia.ent;

import java.lang.reflect.Method;

import inia.Handle;

public class EntityWrapper extends Handle {

    public EntityWrapper(Object handle) {
        super(handle);
    }
    
    public int getId() {
        try {
            Object handle = super.getHandle();
			Class<?> handleClass = handle.getClass();
            Method getId = handleClass.getMethod(
                "getId"
            );
            
            return (int) getId.invoke(handle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
