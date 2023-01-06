package org.crypto.report.dto;

public enum BscDefiTradeLabel {

    HASH("hash"),
    NONCE("nonce"),
    BLOCK_NUMBER("blockNumber"),
    CONTRACT_ADDRESS("contactAddress"),
    FROM("from"),
    TO("to"),
    TOKEN_NAME("tokenName"),
    TOKEN_SYMBOL("tokenSymbol"),
    TOKEN_DECIMAL("tokenDecimal"),
    TIME_STAMP("timeStamp"),
    VALUE("value"),
    GAS("gas"),
    GAS_PRICE("gasPrice"),
    GAS_USED("gasUsed"),
    DATE("Date"),
    OPEN("Open"),
    HIGH("High"),
    LOW("Low"),
    CLOSE("Close");

    public final String label;

    private BscDefiTradeLabel(String label) {
        this.label = label;
    }


}
