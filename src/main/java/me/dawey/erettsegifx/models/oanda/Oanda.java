package me.dawey.erettsegifx.models.oanda;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;

public class Oanda {
    private static final String url = "https://api-fxpractice.oanda.com";
    private static final String accountID = "101-004-30070413-001";
    private static final String token = "84c6afbec99a536c7ee145a1621ebc1c-0fa7b8303562c3be9c0bb9dee0f56b5f";

    private AccountSummary summary;

    public Oanda() {
        Context ctx = new Context(url, token);
        try {
            summary = ctx.account.summary(new AccountID(accountID)).getAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AccountSummary getSummary() {
        return summary;
    }
}
