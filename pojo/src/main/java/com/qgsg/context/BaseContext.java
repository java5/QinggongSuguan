package com.qgsg.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
    public static void setCurrentfingerPrint(String zhiwen) {
        threadLocal1.set(zhiwen);
    }
    public static String getCurrentfingerPrint() {
        return threadLocal1.get();
    }

}
