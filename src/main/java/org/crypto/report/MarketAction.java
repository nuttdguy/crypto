package org.crypto.report;

public enum MarketAction {

    BUY("BUY"),
    SELL("SELL");

    public final String label;

    private MarketAction(String label) {
        this.label = label;
    }

}
