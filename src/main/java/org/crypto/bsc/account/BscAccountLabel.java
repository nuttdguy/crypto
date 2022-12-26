package org.crypto.bsc.account;

public enum BscAccountLabel {

    FUNCTION_NAME("functionName"),
    BLOCK_NUMBER("blockNumber"),
    TIMESTAMP("timeStamp"),
    HASH("hash"),
    NONCE("nonce"),
    BLOCK_HASH("blockHash"),
    TRANSACTION_INDEX("transactionIndex"),
    FROM("from"),
    TO("to"),
    VALUE("value"),
    GAS("gas"),
    GAS_PRICE("gasPrice"),
    IS_ERROR("isError"),
    TX_RECEIPT_STATUS("txreceipt_status"),
    INPUT("input"),
    CONTRACT_ADDRESS("contractAddress"),
    CUMULATIVE_GAS_USED("cumulativeGasUsed"),
    GAS_USED("gasUsed"),
    CONFIRMATIONS("confirmations"),
    METHOD_ID("methodId"),
    TOKEN_NAME("tokenName"),
    TOKEN_SYMBOL("tokenSymbol"),
    TOKEN_DECIMAL("tokenDecimal");

    public final String label;

    private BscAccountLabel(String label) {
        this.label = label;
    }

}
