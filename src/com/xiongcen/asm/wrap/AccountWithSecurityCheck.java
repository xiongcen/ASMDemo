package com.xiongcen.asm.wrap;

import com.xiongcen.asm.SecurityChecker;

/**
 * Created by xiongcen on 17/4/13.
 */
public class AccountWithSecurityCheck implements IAccount {
    private IAccount account;

    public AccountWithSecurityCheck(IAccount IAccount) {
        this.account = IAccount;
    }

    public void operation() {
        SecurityChecker.checkSecurity();
        account.operation();
    }
}
