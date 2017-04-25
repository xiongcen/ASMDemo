package com.xiongcen.asm.really;

import com.xiongcen.asm.SecurityChecker;

/**
 * Created by xiongcen on 17/4/23.
 */
public class AccountASM {

    public void operation() {
        SecurityChecker.checkSecurity();
        System.out.println("operation...");
    }
}
