package org.crypto.report;

import org.crypto.bsc.account.Transaction;

import java.time.LocalDateTime;

/* dto model for a report transaction entry  */
public class TransactionEntry extends Transaction {

    // costBase
    // profitOrLoss
    // onHoldTokens
    // soldTokens
    // generate / write report

    /* tokentx_bsc_account */
    private Double tokenTxValue;
    private String tokenTxFrom;
    private String tokenTxTo;
    private String tokenTxBlockHash;
    private String tokenTxContractAddress;
    private Double tokenTxConfirmations;
    private LocalDateTime tokenTxTimeStamp;
    private Double tokenTxGasUsed;
    private Double tokenTxGas;
    private Double tokenTxGasPrice;
    private String tokenTxTokenName;
    private String tokenTxTokenSymbol;
    private String tokenTxTokenDecimal;

    /* internal_bsc_account */
    private LocalDateTime internalTimeStamp;
    private String internalBlockNumber;
    private String internalFrom;
    private String internalTo;
    private Double internalValue;
    private String internalBlockHash;

    /* tokenlist_bsc_account */
    private String txListBlockHash;
    private Double txListConfirmations;
    private LocalDateTime txListTimeStamp;
    private String txListBlockNumber;
    private Double txListGasUsed;
    private Double txListGas;
    private Double txListGasPrice;
    private String txListFrom;
    private String txListTo;
    private Double txListValue;
    private String txListHash;

    /* bnb_price_history */
    private LocalDateTime bnbPriceDate;
    private Double bnbPriceOpen;
    private Double bnbPriceClose;
    private Double bnbPriceHigh;
    private Double bnbPriceLow;

    public String extractFieldValuesToWrite() {
        return this.getTokenTxValue() + "," +
                this.getTokenTxFrom() + "," +
                this.getTokenTxTo() + "," +
                this.getTokenTxBlockHash() + "," +
                this.getTokenTxContractAddress() + "," +
                this.getTokenTxConfirmations() + "," +
                this.getTokenTxTimeStamp() + "," +
                this.getTokenTxGasUsed() + "," +
                this.getTokenTxGas() + "," +
                this.getTokenTxGasPrice() + "," +
                this.getTokenTxTokenName() + "," +
                this.getTokenTxTokenSymbol() + "," +
                this.getTokenTxTokenDecimal() + "," +

                this.getInternalTimeStamp() + "," +
                this.getInternalBlockNumber() + "," +
                this.getInternalFrom() + "," +
                this.getInternalTo() + "," +
                this.getInternalValue() + "," +
                this.getInternalBlockHash() + "," +

                this.getTxListBlockHash() + "," +
                this.getTxListConfirmations() + "," +
                this.getTxListTimeStamp() + "," +
                this.getTxListBlockNumber() + "," +
                this.getTxListGasUsed() + "," +
                this.getTxListGas() + "," +
                this.getTxListGasPrice() + "," +
                this.getTxListFrom() + "," +
                this.getTxListTo() + "," +
                this.getTxListValue() + "," +
                this.getTxListHash() + "," +
                this.getBnbPriceDate() + "," +
                this.getBnbPriceOpen() + "," +
                this.getBnbPriceClose() + "," +
                this.getBnbPriceHigh() + "," +
                this.getBnbPriceLow();
    }

    public Double getTokenTxValue() {
        return tokenTxValue;
    }

    public void setTokenTxValue(Double tokenTxValue) {
        this.tokenTxValue = tokenTxValue;
    }

    public String getTokenTxFrom() {
        return tokenTxFrom;
    }

    public void setTokenTxFrom(String tokenTxFrom) {
        this.tokenTxFrom = tokenTxFrom;
    }

    public String getTokenTxTo() {
        return tokenTxTo;
    }

    public void setTokenTxTo(String tokenTxTo) {
        this.tokenTxTo = tokenTxTo;
    }

    public String getTokenTxBlockHash() {
        return tokenTxBlockHash;
    }

    public void setTokenTxBlockHash(String tokenTxBlockHash) {
        this.tokenTxBlockHash = tokenTxBlockHash;
    }

    public String getTokenTxContractAddress() {
        return tokenTxContractAddress;
    }

    public void setTokenTxContractAddress(String tokenTxContractAddress) {
        this.tokenTxContractAddress = tokenTxContractAddress;
    }

    public Double getTokenTxConfirmations() {
        return tokenTxConfirmations;
    }

    public void setTokenTxConfirmations(Double tokenTxConfirmations) {
        this.tokenTxConfirmations = tokenTxConfirmations;
    }

    public LocalDateTime getTokenTxTimeStamp() {
        return tokenTxTimeStamp;
    }

    public void setTokenTxTimeStamp(LocalDateTime tokenTxTimeStamp) {
        this.tokenTxTimeStamp = tokenTxTimeStamp;
    }

    public Double getTokenTxGasUsed() {
        return tokenTxGasUsed;
    }

    public void setTokenTxGasUsed(Double tokenTxGasUsed) {
        this.tokenTxGasUsed = tokenTxGasUsed;
    }

    public Double getTokenTxGas() {
        return tokenTxGas;
    }

    public void setTokenTxGas(Double tokenTxGas) {
        this.tokenTxGas = tokenTxGas;
    }

    public Double getTokenTxGasPrice() {
        return tokenTxGasPrice;
    }

    public void setTokenTxGasPrice(Double tokenTxGasPrice) {
        this.tokenTxGasPrice = tokenTxGasPrice;
    }

    public String getTokenTxTokenName() {
        return tokenTxTokenName;
    }

    public void setTokenTxTokenName(String tokenTxTokenName) {
        this.tokenTxTokenName = tokenTxTokenName;
    }

    public String getTokenTxTokenSymbol() {
        return tokenTxTokenSymbol;
    }

    public void setTokenTxTokenSymbol(String tokenTxTokenSymbol) {
        this.tokenTxTokenSymbol = tokenTxTokenSymbol;
    }

    public String getTokenTxTokenDecimal() {
        return tokenTxTokenDecimal;
    }

    public void setTokenTxTokenDecimal(String tokenTxTokenDecimal) {
        this.tokenTxTokenDecimal = tokenTxTokenDecimal;
    }

    public LocalDateTime getInternalTimeStamp() {
        return internalTimeStamp;
    }

    public void setInternalTimeStamp(LocalDateTime internalTimeStamp) {
        this.internalTimeStamp = internalTimeStamp;
    }

    public String getInternalBlockNumber() {
        return internalBlockNumber;
    }

    public void setInternalBlockNumber(String internalBlockNumber) {
        this.internalBlockNumber = internalBlockNumber;
    }

    public String getInternalFrom() {
        return internalFrom;
    }

    public void setInternalFrom(String internalFrom) {
        this.internalFrom = internalFrom;
    }

    public String getInternalTo() {
        return internalTo;
    }

    public void setInternalTo(String internalTo) {
        this.internalTo = internalTo;
    }

    public Double getInternalValue() {
        return internalValue;
    }

    public void setInternalValue(Double internalValue) {
        this.internalValue = internalValue;
    }

    public String getInternalBlockHash() {
        return internalBlockHash;
    }

    public void setInternalBlockHash(String internalBlockHash) {
        this.internalBlockHash = internalBlockHash;
    }

    public String getTxListBlockHash() {
        return txListBlockHash;
    }

    public void setTxListBlockHash(String txListBlockHash) {
        this.txListBlockHash = txListBlockHash;
    }

    public Double getTxListConfirmations() {
        return txListConfirmations;
    }

    public void setTxListConfirmations(Double txListConfirmations) {
        this.txListConfirmations = txListConfirmations;
    }

    public LocalDateTime getTxListTimeStamp() {
        return txListTimeStamp;
    }

    public void setTxListTimeStamp(LocalDateTime txListTimeStamp) {
        this.txListTimeStamp = txListTimeStamp;
    }

    public String getTxListBlockNumber() {
        return txListBlockNumber;
    }

    public void setTxListBlockNumber(String txListBlockNumber) {
        this.txListBlockNumber = txListBlockNumber;
    }

    public Double getTxListGasUsed() {
        return txListGasUsed;
    }

    public void setTxListGasUsed(Double txListGasUsed) {
        this.txListGasUsed = txListGasUsed;
    }

    public Double getTxListGas() {
        return txListGas;
    }

    public void setTxListGas(Double txListGas) {
        this.txListGas = txListGas;
    }

    public Double getTxListGasPrice() {
        return txListGasPrice;
    }

    public void setTxListGasPrice(Double txListGasPrice) {
        this.txListGasPrice = txListGasPrice;
    }

    public String getTxListFrom() {
        return txListFrom;
    }

    public void setTxListFrom(String txListFrom) {
        this.txListFrom = txListFrom;
    }

    public String getTxListTo() {
        return txListTo;
    }

    public void setTxListTo(String txListTo) {
        this.txListTo = txListTo;
    }

    public Double getTxListValue() {
        return txListValue;
    }

    public void setTxListValue(Double txListValue) {
        this.txListValue = txListValue;
    }

    public String getTxListHash() {
        return txListHash;
    }

    public void setTxListHash(String txListHash) {
        this.txListHash = txListHash;
    }

    public LocalDateTime getBnbPriceDate() {
        return bnbPriceDate;
    }

    public void setBnbPriceDate(LocalDateTime bnbPriceDate) {
        this.bnbPriceDate = bnbPriceDate;
    }

    public Double getBnbPriceOpen() {
        return bnbPriceOpen;
    }

    public void setBnbPriceOpen(Double bnbPriceOpen) {
        this.bnbPriceOpen = bnbPriceOpen;
    }

    public Double getBnbPriceClose() {
        return bnbPriceClose;
    }

    public void setBnbPriceClose(Double bnbPriceClose) {
        this.bnbPriceClose = bnbPriceClose;
    }

    public Double getBnbPriceHigh() {
        return bnbPriceHigh;
    }

    public void setBnbPriceHigh(Double bnbPriceHigh) {
        this.bnbPriceHigh = bnbPriceHigh;
    }

    public Double getBnbPriceLow() {
        return bnbPriceLow;
    }

    public void setBnbPriceLow(Double bnbPriceLow) {
        this.bnbPriceLow = bnbPriceLow;
    }
}
