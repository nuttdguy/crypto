package org.crypto.report.dto;

public enum KucoinTradeLabel {

    UID("UID"),
    ACCOUNT_TYPE("Account Type"),
    ORDER_ID("Order ID"),
    SYMBOL("Symbol"),
    SIDE("Side"),
    ORDER_TYPE("Order Type"),
    AVG_FILLED_PRICE("Avg. Filled Price"),
    FILLED_AMOUNT("Filled Amount"),
    FILLED_VOLUME("Filled Volume"),
    FILLED_VOLUME_USDT("Filled Volume (USDT)"),
    FILLED_TIME("Filled Time(UTC-05:00)"),
    FEE("Fee"),
    MAKER_TAKER("Maker/Taker"),
    FEE_CURRENCY("Fee Currency");

    public final String label;

    private KucoinTradeLabel(String label) {
        this.label = label;
    }


}
