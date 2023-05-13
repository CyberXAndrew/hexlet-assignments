package exercise;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        List<String> result = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            NotNull notnull = field.getAnnotation(NotNull.class);
            if (notnull != null) {
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(object);
                    if (fieldValue == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Object object) throws IllegalAccessException {
        Map<String, List<String>> notValidFields = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            NotNull notnull = field.getAnnotation(NotNull.class);
            MinLength minleng = field.getAnnotation(MinLength.class);
            int annoleng = minleng.minLength();
            field.setAccessible(true);
            if (notnull != null && field.get(object) == null) { //аннотация не нал и значение поля не нал
                notValidFields.put(field.getName(), List.of("can not be null"));
            } else if (minleng != null) {
                String fieldValue = (String) field.get(object);
                if (fieldValue.length() < annoleng)
                    notValidFields.put(field.getName(), List.of("length less than " + minleng));
            }
        }
        return notValidFields;
    }
}
// END
