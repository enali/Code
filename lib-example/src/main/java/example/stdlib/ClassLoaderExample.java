package example.stdlib;

public class ClassLoaderExample {
    public static void main(String[] args) {
        String bootPath = System.getProperty("sun.boot.class.path");
        System.out.println(bootPath.replace(";", "\n"));

        System.out.println("==========================");

        String extPath = System.getProperty("java.ext.dirs");
        System.out.println(extPath.replace(";", "\n"));

        System.out.println("==========================");

        String appPath = System.getProperty("java.class.path");
        System.out.println(appPath.replace(";", "\n"));

        System.out.println("==========================");

        System.out.println(ClassLoaderExample.class.getClassLoader());
        System.out.println(int.class.getClassLoader());
    }
}
