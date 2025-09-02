package cn.xiangstudy.generalproject.config.threadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用上下文管理器
 * @author zhangxiang
 * @date 2025-09-02 10:15
 */
public class ContextManager {
    private static final Map<ContextKey<?>, ThreadLocal<?>> contextMap = new ConcurrentHashMap<>();

    /**
     * 设置值
     * @author zhangxiang
     * @date 2025/9/2 10:21
     * @param key
     * @param value
     */
    public static <T> void set(ContextKey<T> key, T value) {
        @SuppressWarnings("unchecked")
        ThreadLocal<T> threadLocal = (ThreadLocal<T>) contextMap.computeIfAbsent(key, k -> new ThreadLocal<>());
        threadLocal.set(value);
    }

    /**
     * 获取值
     * @author zhangxiang
     * @date 2025/9/2 10:25
     * @param key
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(ContextKey<T> key) {
        ThreadLocal<T> threadLocal = (ThreadLocal<T>) contextMap.get(key);
        return threadLocal == null ? null : threadLocal.get();
    }

    /**
     * 移除指定key
     * @author zhangxiang
     * @date 2025/9/2 10:27
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> void remove(ContextKey<T> key) {
        ThreadLocal<T> threadLocal = (ThreadLocal<T>) contextMap.get(key);
        if (threadLocal != null) {
            threadLocal.remove();
        }
    }

    /**
     * 清空所有上下文
     * @author zhangxiang
     * @date 2025/9/2 10:28
     */
    public static void clear(){
        for(ThreadLocal<?> threadLocal: contextMap.values()){
            threadLocal.remove();
        }
    }
}
