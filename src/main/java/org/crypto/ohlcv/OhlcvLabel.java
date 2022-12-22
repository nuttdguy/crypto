package org.crypto.ohlcv;

public enum OhlcvLabel {
    ID("id"),
    NAME("name"),
    SYMBOL("symbol"),
    TIME_OPEN("time_open"),
    TIME_CLOSE("time_close"),
    TIME_HIGH("time_high"),
    TIME_LOW("time_low"),
    OPEN("open"),
    HIGH("high"),
    LOW("low"),
    CLOSE("close"),
    VOLUME("volume"),
    MARKET_CAP("market_cap"),
    TIMESTAMP("timestamp"),
    QUOTE_CURRENCY("quote_currency");

    public final String label;

    private OhlcvLabel(String label) {
        this.label = label;
    }
}
