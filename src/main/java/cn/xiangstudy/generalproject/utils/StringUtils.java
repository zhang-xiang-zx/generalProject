package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.config.constant.SysConst;
import cn.xiangstudy.generalproject.pojo.entity.SysToken;
import com.alibaba.fastjson2.JSON;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author zhangxiang
 * @date 2025-07-16 11:19
 */
@Slf4j
public class StringUtils {

    /**
     * 判断字符串是否是空串，全是空格也算空串
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

    /**
     * 随机盐值
     * @return java.lang.String
     * @author zhangxiang
     * @date 2025/7/22 10:01
     */
    public static String randomSlatStr(int length) {

        String LowercaseLetter = "abcdefghijklmnopqrstuvwxyz";
        String capitalLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        Random random = new Random();
        StringBuilder result = new StringBuilder();
        while (result.length() < length) {
            int is = random.nextInt(3);

            int i;
            String str;
            if (is == 1) {
                i = random.nextInt(capitalLetter.length());
                str = capitalLetter;
            } else if (is < 1) {
                i = random.nextInt(LowercaseLetter.length());
                str = LowercaseLetter;
            } else {
                i = random.nextInt(numbers.length());
                str = numbers;
            }

            result.append(str.charAt(i));
        }

        return result.toString();
    }

    /**
     * 通过base64进行编码
     * @param obj
     * @return java.lang.String
     * @author zhangxiang
     * @date 2025/7/22 10:29
     */
    public static String encoderByBase64(Object obj) {
        String valueStr = JSON.toJSONString(obj);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(valueStr.getBytes());
    }

    /**
     * 通过base64进行解码
     * @param encodeStr
     * @return java.lang.String
     * @author zhangxiang
     * @date 2025/7/22 10:35
     */
    public static String decoderByBase64(String encodeStr) {
        byte[] decode = Base64.getUrlDecoder().decode(encodeStr);
        return new String(decode);
    }

    /**
     * 判断手机号格式是否正确
     * @param phone
     * @return boolean true:符合, false:不符合
     * @author zhangxiang
     * @date 2025/7/22 15:26
     */
    public static boolean checkPhone(String phone) {
        boolean flag = false;

        if (!isEmpty(phone)) {
            String phonePattern = "^1[3-9]\\d{9}$";
            Pattern pattern = Pattern.compile(phonePattern);
            flag = pattern.matcher(phone).matches();
        }

        return flag;
    }

    /**
     * 判断昵称是否是2到8个字符
     * @param nickname
     * @return boolean true:符合, false:不符合
     * @author zhangxiang
     * @date 2025/7/22 15:59
     */
    public static boolean checkNickname(String nickname) {
        boolean flag = false;
        if (!isEmpty(nickname)) {
            String nicknamePattern = "^.{2,8}$";
            flag = Pattern.matches(nicknamePattern, nickname);
        }

        return flag;
    }

    /**
     * 判断密码格式; 至少包含大小写数字,特殊字符
     * @param password
     * @return boolean true:符合, false:不符合
     * @author zhangxiang
     * @date 2025/7/22 16:06
     */
    public static boolean checkPassword(String password) {
        boolean flag = false;

        if (!isEmpty(password)) {
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$";
            flag = Pattern.matches(passwordPattern, password);
        }

        return flag;
    }

    /**
     * 生成账号
     * @param initialValue 起始账号
     * @param size         生成个数
     * @return java.util.List<java.lang.String>
     * @author zhangxiang
     * @date 2025/7/24 09:41
     */
    public static List<String> generateAccount(Long initialValue, Integer size) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(String.valueOf(initialValue++));
        }

        return result;
    }

    /**
     * md5加密
     * @author zhangxiang
     * @date 2025/7/29 17:19
     * @param str
     * @return java.lang.String
     */
    public static String encodeMD5(String str) {

        StringBuilder result = new StringBuilder();

        try {
            // 获取md5实例
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            // 计算哈希值
            byte[] digest = md5.digest(str.getBytes());

            for (byte b : digest) {
                String hexString = Integer.toHexString(0xff & b);
                if (hexString.length() == 1) {
                    result.append('0');
                }
                result.append(hexString);
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    /**
     * 获取字符串后几位
     * @author zhangxiang
     * @date 2025/7/28 17:14
     * @param str
     * @param num
     * @return java.lang.String
     */
    public static String getEndStr(String str, int num){
        int length = str.length();
        if(num > length){
            throw new RuntimeException("num is to big");
        }
        return str.substring( length - num);
    }
    
    /**
     * token加盐
     * @author zhangxiang
     * @date 2025/7/29 17:31 
     * @return java.lang.String
     */
    public static String encodeToken(String token){

        String tokenSlat = randomSlatStr(SysConst.TOKEN_SLAT_NUM);

        String reverse = new StringBuilder(token).reverse().toString();

        return reverse + tokenSlat;
    }

    public static void main(String[] args) {
        String s = encodeToken("abcdefg");
        System.out.println(s);
    }

}
