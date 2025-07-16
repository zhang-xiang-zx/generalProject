package cn.xiangstudy.generalproject.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串工具类
 *
 * @author zhangxiang
 * @date 2025-07-16 11:19
 */
@Slf4j
public class StringUtils {

    /**
     * 判断字符串是否是空串，全是空格也算空串
     *
     * @param str
     * @return boolean
     * @author zhangxiang
     * @date 2025/7/16 11:23
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 根据位数不足补零，充足返回原字符串;自动去除空格
     *
     * @param str
     * @param size
     * @return java.lang.String
     * @author zhangxiang
     * @date 2025/7/16 11:45
     */
    public static String complementZero(String str, int size) {

        String[] split = str.trim().split("");

        int length = split.length;

        StringBuilder sb = new StringBuilder();

        if (size > length) {
            int cha = size - length;
            sb.append("0".repeat(cha));
        }

        return sb.append(str.trim()).toString();

    }
}
