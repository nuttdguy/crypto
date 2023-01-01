package org.crypto.exchange.kucoin;

public enum ActionType {

    SUB_USER("sub/user"),
    ACCOUNTS("accounts"),
    ACCOUNTS_LEDGERS("accounts/ledgers"),
    USERINFO("user-info");


    public final String label;

    private ActionType(String label) {
        this.label = label;
    }

}
