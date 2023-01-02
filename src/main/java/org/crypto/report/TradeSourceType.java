package org.crypto.report;

public enum TradeSourceType {

    KUCOIN_SPOT_TRADE("kucoin_spot_trade_transaction"),
    KUCOIN_FUTURE_TRADE("kucoin_future_trade_transaction");

    public final String label;

    private TradeSourceType(String label) {
        this.label = label;
    }

}
