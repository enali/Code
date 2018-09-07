package example.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助CGLib直接操纵字节码, 在运行时生成大量动态类, 制造元空间溢出OOM
 *
 * VMargs: -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class JavaMetaspaceOOM {
    public static void main(String[] args) {
        while (true) {
            // 生成动态子类来使能方法拦截
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HeapOOM.OOMObject.class);
            // 是否使用已生成类的静态缓存
            enhancer.setUseCache(false);
            // 不能简单用lambda, 好像lambda没办法抛出异常
//            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invokeSuper(obj, args1));
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }
}
