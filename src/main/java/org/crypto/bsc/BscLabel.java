package org.crypto.bsc;

public enum BscLabel {
    TOKEN_HASH("tx_hash"),
    UNIX_TIMESTAMP("unix_timestamp"),
    DATE_TIME("date_time"),
    FROM("from"),
    TO("to"),
    TOKEN_VALUE("token_value"),
    USD_VALUE_DAY_OF_TX("usd_value_day_of_tx"),
    CONTRACT_ADDRESS("contract_address"),
    TOKEN_NAME("token_name"),
    TOKEN_SYMBOL("token_symbol");

    public final String label;

    private BscLabel(String label) {
        this.label = label;
    }

}
