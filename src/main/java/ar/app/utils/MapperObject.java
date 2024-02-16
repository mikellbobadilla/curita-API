package ar.app.utils;

import java.lang.reflect.Field;

/**
 * This class is used to update the data from the source object to the target object.
 * @param <T> The target object
 * @param <S> The source object
 */
public class MapperObject<T, S> {

    /**
     * Update the data from the source object to the target object.
     * @param target The target object
     * @param source The source object
     * @return The target object with the updated data
     * @throws IllegalAccessException If the field is not accessible
     */
    public T mapData(T target, S source) throws IllegalAccessException {
        Field[] targetFields = getFields(target.getClass());
        Field[] sourceFields = getFields(source.getClass());

        for (Field targetField: targetFields) {
            targetField.setAccessible(true);
            for (Field sourceField: sourceFields) {
                sourceField.setAccessible(true);
                String targetProp = getProp(targetField);
                String targetType = getType(targetField);
                String sourceProp = getProp(sourceField);
                String sourceType = getType(sourceField);
                boolean isMatchProp = isMatch(targetProp, sourceProp);
                boolean isMatchType = isMatch(targetType, sourceType);
                if (isMatchProp && isMatchType) {
                    Object value = sourceField.get(source);
                    if (value != null)
                        targetField.set(target, value);
                }
                sourceField.setAccessible(false);
            }
            targetField.setAccessible(false);
        }
        return target;
    }

    private boolean isMatch(String target, String source) {
        return target.equals(source);
    }

    private Field[] getFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    private String getProp(Field field) {
        return field.getName();
    }

    private String getType(Field field) {
        return field.getType().getName();
    }
}
