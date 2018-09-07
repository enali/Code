package example.stdlib;

public class ClassLoaderExample {
    public static void main(String[] args) {
        // 启动类加载器的加载路径
        String bootPath = System.getProperty("sun.boot.class.path");
        System.out.println(bootPath.replace(";", "\n"));

        System.out.println("==========================");

        // 拓展类加载器的加载路径
        String extPath = System.getProperty("java.ext.dirs");
        System.out.println(extPath.replace(";", "\n"));

        System.out.println("==========================");

        // 应用类加载器的加载路径, 更准确地, 这里java所有的加载路径, 包括上面2种
        String appPath = System.getProperty("java.class.path");
        System.out.println(appPath.replace(";", "\n"));

        System.out.println("==========================");

        // 默认的用户类由应用类加载器加载
        ClassLoader cl = ClassLoaderExample.class.getClassLoader();
        System.out.println(cl);  // 应用类加载器
        System.out.println(cl.getParent());  // 拓展类加载器
        System.out.println(cl.getParent().getParent());  // null, 因为启动类加载器是C++写的, 并不在Java中

        // 系统的类由启动类加载器加载
        System.out.println(int.class.getClassLoader());
    }
}
