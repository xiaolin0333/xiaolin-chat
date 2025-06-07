package pers.xiaolin.context;

/**
 * BaseContext:TreadLocal的工具类
 * TreadLocal:每个线程单独一份存储空间，有线程隔离的效果
 */


public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // 存值
    public static void setCurrentId(String id) {
        threadLocal.set(id);
    }

    // 取值
    public static String getCurrentId() {
        return threadLocal.get();
    }

    // 移除值
    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
