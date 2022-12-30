package org.crypto.bsc.account;

public enum TxActionType {
    TX_LIST("txlist"),
    TX_LIST_INTERNAL("txlistinternal"),
    TOKEN_TX("tokentx");

    public final String label;

    private TxActionType(String label) {
        this.label = label;
    }

}
