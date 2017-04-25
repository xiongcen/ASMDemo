package com.xiongcen.asm.really;

import com.sun.xml.internal.ws.org.objectweb.asm.MethodAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

/**
 * Created by xiongcen on 17/4/14.
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {

    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(mv);
    }

    @Override
    public void visitCode() {
        // ClassReader读到每个方法的首部时调用 visitCode()，在这个重写方法里，
        // 我们用 visitMethodInsn(Opcodes.INVOKESTATIC, "com/xiongcen/asm/SecurityChecker","checkSecurity", "()V");插入了安全检查功能。
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/xiongcen/asm/SecurityChecker",
                "checkSecurity", "()V");
    }
}
