package com.example.demo.core.utility;

import java.lang.reflect.Field;
import java.util.Arrays;

public class BeanUtilsCustom {
    private BeanUtilsCustom() {
    }

    public static <S, B, P> void copyDeepProperties(S source, S target, Class<B> stopClass) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        while (sourceClass != null && !sourceClass.equals(stopClass)) {
            Field[] fields = sourceClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Field targetField = targetClass.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, field.get(source));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // اگر فیلد موجود نیست یا مشکلی در دسترسی وجود دارد، آن را نادیده بگیرید
                }
            }
            sourceClass = sourceClass.getSuperclass();
        }
    }

    public static <S, T> void updateEntityDynamically(S source, T target) {
        if (source == null || target == null) {
            return;
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);

            try {
                Object sourceValue = sourceField.get(source);
                if (sourceValue == null) {
                    continue; // مقدار null نادیده گرفته می‌شود
                }

                String fieldName = sourceField.getName();

                try {
                    Field targetField = target.getClass().getDeclaredField(fieldName);
                    targetField.setAccessible(true);

                    Class<?> targetType = targetField.getType();

                    // پشتیبانی از Enum
                    if (targetType.isEnum() && sourceValue instanceof String strValue) {
                        Object enumValue = Arrays.stream(targetType.getEnumConstants())
                                .filter(e -> ((Enum<?>) e).name().equalsIgnoreCase(strValue))
                                .findFirst()
                                .orElseThrow(() ->
                                        new IllegalArgumentException("Invalid enum value for field: " + fieldName));

                        targetField.set(target, enumValue);
                    }
                    // اگر نوع یکسان بود یا قابل انتساب بود
                    else if (targetType.isAssignableFrom(sourceValue.getClass())) {
                        targetField.set(target, sourceValue);
                    }

                } catch (NoSuchFieldException e) {

                }

            } catch (IllegalAccessException e) {
            }
        }
    }

}
