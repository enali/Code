package example.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class GrandFatherTest {
    private class GrandFather {
        void think() {
            System.out.println("I am grandfather");
        }
    }
    private class Father extends GrandFather {
        @Override
        void think() {
            System.out.println("I am father");
        }
    }
    private class Son extends Father {
        @Override
        void think() {
            // TODO: 试图调用GrandFather的think(), 但是目前仍然是失败
            // 虽然可以通过super调用Father的think()
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "think", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {

            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherTest().new Father()).think();
    }
}
