package org.crypto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* Class the extract class fields; format the header row to snake case */
public class ClassFieldExtractor {

    private ClassFieldExtractor() { }

    /* extract the field names of the class */
    public static List<String> extractDeclaredFields(Class<?> clazz) {
        // init an array to store the field names
        List<String> fields = new ArrayList<>();

        // extract all fields from super class
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            List<String> superFields = extractDeclaredFields(superClass);
            fields.addAll(superFields);
        }

        // extract all fields from the subclass
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
        }

        return fields;
    }

    /* transform camel case names to snake case */
    public static String camelCaseToSnakeCase(String camelCase) {

        StringBuilder sb = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
