package cn.xiangstudy.generalproject.config.threadLocal;

/**
 * 上下文Key
 * @author zhangxiang
 * @date 2025-09-02 10:10
 */
public class ContextKey<T> {

    private final String name;

    private final Class<T> type;

    private ContextKey(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public static <T> ContextKey<T> of(String name, Class<T> type) {
        return new ContextKey<>(name, type);
    }

    public String getName(){
        return name;
    }

    public Class<T> getType() {
        return type;
    }
}
