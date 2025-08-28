package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.pojo.entity.SysRole;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对象处理类
 * @author zhangxiang
 * @date 2025-08-27 17:37
 */
public class ObjectUtils {

    /**
     * 克隆对象属性
     * @author zhangxiang
     * @date 2025/8/28 09:03
     * @param source
     * @param target
     */
    public static void cloneProperties(Object source, Object target) {

        if(source == null || target == null) {
            throw new NullPointerException("source or target are null");
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

//        Field[] sourceFields = sourceClass.getDeclaredFields();
        List<Field> sourceFields = getAllFields(sourceClass);

        for(Field sourceField : sourceFields) {

            try{
                Field targetField = null;
                try{
//                    targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField = findField(targetClass, sourceField.getName());
                    if(targetField == null){
                        continue;
                    }
                }catch (Exception e){
                    continue;
                }

                // 检查类型
                if(!sourceField.getType().equals(targetField.getType())) {
                    continue;
                }

                sourceField.setAccessible(true);
                targetField.setAccessible(true);

                Object value = sourceField.get(source);
                targetField.set(target, value);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 克隆对象属性到指定类型的新对象
     * @author zhangxiang
     * @date 2025/8/28 09:17
     * @param source
     * @param targetClass
     * @return T
     */
    public static <T> T cloneProperties(Object source, Class<T> targetClass) {

        if(source == null || targetClass == null) {
            throw new NullPointerException("source or target are null");
        }

        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            cloneProperties(source, target);
            return target;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取对象所有字段, 包括继承类中的字段
     * @author zhangxiang
     * @date 2025/8/28 09:56
     * @param clazz
     * @return java.util.List<java.lang.reflect.Field>
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {

            Field[] declaredFields = clazz.getDeclaredFields();

            for(Field field : declaredFields) {
                fields.add(field);
            }

            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * 在类及其父类中查找字段
     * @author zhangxiang
     * @date 2025/8/28 10:06
     * @param clazz
     * @param fieldName
     * @return java.lang.reflect.Field
     */
    public static Field findField(Class<?> clazz, String fieldName) {

        while (clazz != null && clazz != Object.class) {
            try{
                return clazz.getDeclaredField(fieldName);
            }catch (Exception e){
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    public static void main(String[] args) {
        SysRole admin = SysRole.builder().roleName("admin").createTime(new Date()).build();

        SysRole sysRole = cloneProperties(admin, SysRole.class);
        System.out.println(sysRole.toString());
        System.out.println(sysRole.getCreateTime());

//        List<Field> allFields = getAllFields(admin.getClass());
//        allFields.forEach(field -> {
//            System.out.println(field.getName());
//        });

    }
}
