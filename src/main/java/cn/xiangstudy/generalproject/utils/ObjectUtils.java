package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.config.annotation.FieldValueNotNull;
import cn.xiangstudy.generalproject.config.annotation.UpdateFieldIsNull;
import cn.xiangstudy.generalproject.pojo.dto.UpdateRoleDTO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象处理类
 *
 * @author zhangxiang
 * @date 2025-08-27 17:37
 */
public class ObjectUtils {

    /**
     * 克隆对象属性
     *
     * @param source
     * @param target
     * @author zhangxiang
     * @date 2025/8/28 09:03
     */
    public static void cloneProperties(Object source, Object target) {

        if (source == null || target == null) {
            throw new NullPointerException("source or target are null");
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

//        Field[] sourceFields = sourceClass.getDeclaredFields();
        List<Field> sourceFields = getAllFields(sourceClass);

        for (Field sourceField : sourceFields) {

            try {
                Field targetField = null;
                try {
//                    targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField = findField(targetClass, sourceField.getName());
                    if (targetField == null) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }

                // 检查类型
                if (!sourceField.getType().equals(targetField.getType())) {
                    continue;
                }

                sourceField.setAccessible(true);
                targetField.setAccessible(true);

                Object value = sourceField.get(source);
                targetField.set(target, value);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 克隆对象属性到指定类型的新对象
     *
     * @param source
     * @param targetClass
     * @return T
     * @author zhangxiang
     * @date 2025/8/28 09:17
     */
    public static <T> T cloneProperties(Object source, Class<T> targetClass) {

        if (source == null || targetClass == null) {
            throw new NullPointerException("source or target are null");
        }

        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            cloneProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取对象所有字段, 包括继承类中的字段
     *
     * @param clazz
     * @return java.util.List<java.lang.reflect.Field>
     * @author zhangxiang
     * @date 2025/8/28 09:56
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {

            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field field : declaredFields) {
                fields.add(field);
            }

            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * 在类及其父类中查找字段
     *
     * @param clazz
     * @param fieldName
     * @return java.lang.reflect.Field
     * @author zhangxiang
     * @date 2025/8/28 10:06
     */
    public static Field findField(Class<?> clazz, String fieldName) {

        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    /**
     * 判断对象中是否都为空
     *
     * @param object
     * @return boolean
     * @author zhangxiang
     * @date 2025/8/28 15:03
     */
    public static boolean isAllNull(Object object) {
        boolean flag = true;

        if (object == null) {
            throw new NullPointerException("object is null");
        }

        Class<?> aClass = object.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {
            try {
                field.setAccessible(true);
                Object o = field.get(object);

                if (o != null) {
                    flag = false;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return flag;
    }


    /**
     * 判断对象中带有不为空注解的字段是否有值, 有一个为空就为true, 都不为空就是false
     *
     * @param obj
     * @return boolean
     * @author zhangxiang
     * @date 2025/8/28 15:19
     */
    public static boolean isAnnotationNull(Object obj) {
        boolean flag = false;

        if (obj == null) {
            throw new NullPointerException("obj is null");
        }

        Class<?> aClass = obj.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof FieldValueNotNull) {
                    System.out.println(field.getName());
                    field.setAccessible(true);
                    try {
                        Object value = field.get(obj);
                        if (value == null) {
                            flag = true;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return flag;
    }

    /**
     * 判断修改对象中, 需要修改的字段是否都为空, 其中ID条件一定不能为空
     * @author zhangxiang
     * @date 2025/8/28 15:43
     * @param obj
     * @return boolean true: 传来的字段都为空, false: 不都为空
     */
    public static boolean isUpdateObjectNull(Object obj) {
        boolean flag = false;
        int haveNotNullFieldNum = 0;
        int isNull = 0;

        if (obj == null) {
            throw new NullPointerException("obj is null");
        }

        Class<?> aClass = obj.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {

            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {

                // 修改对象中 ID 是一定要传的, 没有直接判空
                if(annotation instanceof FieldValueNotNull){
                    field.setAccessible(true);
                    try {
                        Object id = field.get(obj);
                        if(id == null){
                            flag = true;
                            break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if(annotation instanceof UpdateFieldIsNull){
                    haveNotNullFieldNum++;
                    field.setAccessible(true);
                    try{
                        Object value = field.get(obj);
                        if(value == null){
                            isNull++;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        // 判断拥有的注解是否都为空
        if(haveNotNullFieldNum == isNull){
            flag = true;
        }

        return flag;
    }

}
