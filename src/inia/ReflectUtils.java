package inia;

public class ReflectUtils {
    
    public static Class<?> getNmsClass(String className) {
        try {
            McVersion v = McVersion.CURRENT_VERSION;
            String nmsPkg = v.getNmsPkg();
            String nmsClassName = nmsPkg + "." + className;

			return Class.forName(nmsClassName);
		} catch (ClassNotFoundException exception) {
			return null;
        }
    }

    public static Class<?> getNmsSubclass(Class<?> nmsClass, int index) {
        if(nmsClass == null) return null;
        
        try {
            return nmsClass.getDeclaredClasses()[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
