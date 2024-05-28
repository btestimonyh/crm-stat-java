package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.model.lead.PostLeadDto;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetClassFieldsService {
    public List<String> getClassFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).toList();
    }


    public static void execute() {
        // Get the class object
        Class<?> clazz = PostLeadDto.class;

        // Create a map to hold field names and their corresponding setter methods
        Map<String, Method> fieldSetterMap = new HashMap<>();

        // Get all declared fields
        Field[] fields = clazz.getDeclaredFields();

        // Get all declared methods
        Method[] methods = clazz.getDeclaredMethods();

        // Populate the map
        for (Field field : fields) {
            String fieldName = field.getName();
            String setterName = "set" + capitalize(fieldName);

            for (Method method : methods) {
                if (isSetterForField(method, fieldName)) {
                    fieldSetterMap.put(fieldName, method);
                    break;
                }
            }
        }

        // Print the map
        for (Map.Entry<String, Method> entry : fieldSetterMap.entrySet()) {
            System.out.println("Field: " + entry.getKey() + ", Setter: " + entry.getValue().getName());
        }
    }

    // Helper method to determine if a method is a setter for a specific field
    private static boolean isSetterForField(Method method, String fieldName) {
        return method.getName().equals("set" + capitalize(fieldName)) &&
                method.getParameterCount() == 1 &&
                method.getReturnType().equals(void.class);
    }

    // Helper method to capitalize the first letter of a string
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
