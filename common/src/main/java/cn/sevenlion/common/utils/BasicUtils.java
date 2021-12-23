package cn.sevenlion.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: qimeiwen
 * @create: 2021-10-08
 */
public class BasicUtils {

    public static Snowflake snowflake = IdUtil.createSnowflake(1,1);

    /**
     * 生成唯一主键
     * @return
     */
    public static String getSerialCode() {
        return String.valueOf(snowflake.nextId());
    }

    public static boolean isPhone(String mobile) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[5,6])|(17[0-8])|(18[0-9])|(19[1、5、8、9]))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    @SneakyThrows
    public static <T> T convert(Object source, Class<T> clazz) {
        //返回的对象
        T bean = (T) clazz.newInstance();
        //返回对象的字段
        Field[] beanFields = clazz.getDeclaredFields();
        Map<String, Field> beanFieldMap = Stream.of(beanFields).collect(Collectors.toMap(Field::getName, Function.identity()));

        //得到源对象的字段和方法
        Class sourceClass = source.getClass();
        //得到父类
        Class sourceSuperClass = sourceClass.getSuperclass();
        List<Field> fieldList = Lists.newArrayList(sourceClass.getDeclaredFields());
        fieldList.addAll(Lists.newArrayList(sourceSuperClass.getDeclaredFields()));
        for (Field sourceField : fieldList) {
            sourceField.setAccessible(true);
            Field field = beanFieldMap.get(sourceField.getName());
            if (field != null) {
                field.setAccessible(true);
                field.set(bean, sourceField.get(source));
            }
        }
        return bean;
    }
}
