package cn.xiangstudy.generalproject.config.response;

/**
 * 自定义异常类
 * @author zhangxiang
 * @date 2025-07-24 15:37
 */
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
