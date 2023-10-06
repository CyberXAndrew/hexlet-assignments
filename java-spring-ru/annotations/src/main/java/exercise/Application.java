package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                try {
                    String methodName = method.getName();
                    var methodType = method.getReturnType().getSimpleName();
//                    String typeShortName =
//                            methodType.equals(String.class) ? "String" : methodType.getTypeName();
                    System.out.printf("Method %s returns a value of type %s%n", methodName,
                            methodType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // END
    }
}
