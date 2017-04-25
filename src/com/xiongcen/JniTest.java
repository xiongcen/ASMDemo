package com.xiongcen;

/**
 * Created by xiongcen on 17/2/25.
 */
public class JniTest {
    public static native String sayHello(String name); // 1.声明这是一个native函数，由本地代码实现

    public static void main(String[] args) {
        String text = sayHello("xiongcen");  // 3.调用本地函数
        System.out.println(text);

//        String libraryDirs = System.getProperty("java.library.path");
//        System.out.println(libraryDirs);
    }

    static {
//        System.load("/Users/xiongcen/Documents/IdeaProject/JniProject/jni/libHelloWorld.jnilib");   // 2.加载实现了native函数的动态库，只需要写动态库的名字
        System.loadLibrary("HelloWorld");
    }

}
