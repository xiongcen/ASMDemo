package com.xiongcen.asm.proxy;

import com.xiongcen.asm.SecurityChecker;
import com.xiongcen.asm.wrap.IAccount;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiongcen on 17/4/14.
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {

    private Object proxyedObject;

    public SecurityProxyInvocationHandler(Object o) {
        proxyedObject = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (proxy instanceof IAccount && "operation".equals(method.getName())) {
            SecurityChecker.checkSecurity();
        }
        return method.invoke(proxyedObject, args);
    }
}
