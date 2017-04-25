package com.xiongcen.asm.really;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;

/**
 * Created by xiongcen on 17/4/14.
 * <p/>
 * ClassAdapter是 ASM 框架提供的一个默认类，负责沟通 ClassReader和 ClassWriter.
 * 如果想要改变 ClassReader处读入的类，然后从 ClassWriter处输出，可以重写相应的 ClassAdapter函数.
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    // 重写 visitMethod，访问到 "operation" 方法时，给出自定义 MethodVisitor，实际改写方法内容
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        System.out.println("AddSecurityCheckClassAdapter;" + "name:" + name + ";desc:" + desc + ";signature:" + signature);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            if ("operation".equals(name)) {
                // 使用自定义的MethodVisitor,实际改写方法内容
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
