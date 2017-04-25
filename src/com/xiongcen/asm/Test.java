package com.xiongcen.asm;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import com.xiongcen.asm.proxy.SecurityProxyInvocationHandler;
import com.xiongcen.asm.really.AccountASM;
import com.xiongcen.asm.really.AddSecurityCheckClassAdapter;
import com.xiongcen.asm.wrap.AccountWithSecurityCheck;
import com.xiongcen.asm.wrap.IAccount;
import com.xiongcen.asm.wrap.AccountImpl;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by xiongcen on 17/4/14.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        // 1.使用包装类
//        AccountWithSecurityCheck account = new AccountWithSecurityCheck(new AccountImpl());
//        account.operation();

        // 2.使用代理
//        IAccount account = (IAccount) Proxy.newProxyInstance(
//                IAccount.class.getClassLoader(),
//                new Class[]{IAccount.class},
//                new SecurityProxyInvocationHandler(new AccountImpl()));
//        account.operation();

        // 3.使用ASM
        // 使用 ClassReader 去读取 Account 类的字节码信息。
        ClassReader cr = new ClassReader("com.xiongcen.asm.Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        // 通过accept方法扫描整个字节码,SKIP_DEBUG选项的意义是在扫描过程中掠过所有有关行号方面的内容
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("/Users/xiongcen/Documents/IdeaProject/ASMDemo/out/production/ASMDemo/com/xiongcen/asm/Account.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();

        Account account = new Account();
        account.operation();

    }
}
