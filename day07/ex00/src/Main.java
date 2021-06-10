import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> showClass(Class c) {
        System.out.println("methods:");
        Method[] methods = c.getDeclaredMethods();
        Field[] fields = c.getDeclaredFields();
        ArrayList<String> list = new ArrayList<>();
        for (Method method : methods)
        {
            if (method.getName().equals("toString"))
                continue;
            System.out.println("\t\t" + method.getReturnType() + " " + method.getName() + "(" + method.getGenericReturnType() + ")");
        }
        System.out.println("fields:");
        for (Field field : fields) {
            if (field.getName().equals("toString"))
                continue;
            System.out.println("\t\t" + field.getType().getSimpleName() + " " + field.getName());
            list.add(field.getName());
        }
        System.out.println("------------------");
        System.out.println("Let's create an object.");
        return list;
    }
    public static myClasses createObject(String className, Scanner sc, ArrayList<String> list) {
        myClasses myClass = null;
        try {
            Class c = Class.forName(className);
            myClass = (myClasses) c.newInstance();
            list = showClass(c);
            for (String item : list) {
                System.out.println(item + ":");
                String tmp = sc.nextLine();
                Field f = myClass.getClass().getDeclaredField(item);
                f.setAccessible(true);
                if (f.getType().getSimpleName().toString().equals("String"))
                    f.set(myClass, (String) tmp);
                else
                    f.set(myClass, Integer.parseInt(tmp));
            }
        } catch (ClassNotFoundException | NoClassDefFoundError | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            System.out.println("Class not found");
            System.exit(1);
        }
        return myClass;
    }
    public static myClasses changeField(Scanner sc, myClasses myClass) {
        try {
            String fieldToChange = sc.next();
            Field f = myClass.getClass().getDeclaredField(fieldToChange);
            f.setAccessible(true);
            System.out.println("Enter " + f.getType().getSimpleName() + " value");
            String newValue = sc.next();
            if (f.getType().getSimpleName().equals("int")) {
                f.set(myClass, Integer.parseInt(newValue));
            } else
                f.set(myClass, (String) newValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return myClass;
    }
    public static myClasses changeMethod(Scanner sc, myClasses myClass) {
        try {
            String methodToChange = sc.next();
            Method[] m = myClass.getClass().getDeclaredMethods();
            for (Method item : m) {
                if (item.getName().equals(methodToChange.substring(0, methodToChange.indexOf('(')))) {
                    item.setAccessible(true);
                    Class<?>[] parameter_types = item.getParameterTypes();
                    for (Class<?> param : parameter_types) {
                        System.out.println("Enter " + param.getSimpleName() + " value");
                        String newValue = sc.next();
                        System.out.print("Method returned: ");
                        if (param.equals(int.class)) {
                            Object value = item.invoke(myClass, Integer.parseInt(newValue));
                            System.out.println((int)value);
                        } else if (param.equals(String.class)) {
                            Object value = item.invoke(myClass, newValue);
                            System.out.println((String)value);
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Bad Argument");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("No such method");
        }
        return myClass;
    }
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<String> list = new ArrayList<>();
            System.out.println("Classes:" + "\n\t" +
                    "- User" + "\n\t" +
                    "- Car" + "\n" +
                    "------------------" + "\n" +
                    "Enter class name:");

            String className = sc.nextLine();
            System.out.println("------------------");

            myClasses myClass = createObject(className, sc, list);
            System.out.println("Object created: " + myClass + "\n" +
                    "------------------" + "\n" +
                    "Enter name of the field for changing:");
            myClass = changeField(sc, myClass);
            System.out.println("Object updated: " + myClass + "\n" +
                    "------------------" + "\n" +
                    "Enter name of the method for call:");
            myClass = changeMethod(sc, myClass);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect values");
        }
    }
}