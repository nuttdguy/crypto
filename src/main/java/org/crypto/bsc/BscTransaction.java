package org.crypto.bsc;

import org.crypto.Transaction;

import java.time.LocalDateTime;

public class BscTransaction extends Transaction {

    private final String txHash;
    private final long unixTimestamp;
    private final LocalDateTime dateTime;
    private final String from;
    private final String to;
    private final double tokenValue;
    private final double usdValueDayOfTx;
    private final String contractAddress;
    private final String tokenName;
    private final String tokenSymbol;

    public BscTransaction(String txHash,
                          long unixTimestamp,
                          LocalDateTime dateTime,
                          String from,
                          String to,
                          double tokenValue,
                          double usdValueDayOfTx,
                          String contractAddress,
                          String tokenName,
                          String tokenSymbol) {
        this.txHash = txHash;
        this.unixTimestamp = unixTimestamp;
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
        this.tokenValue = tokenValue;
        this.usdValueDayOfTx = usdValueDayOfTx;
        this.contractAddress = contractAddress;
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
    }

    public String getTxHash() {
        return txHash;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getTokenValue() {
        return tokenValue;
    }

    public double getUsdValueDayOfTx() {
        return usdValueDayOfTx;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    @Override
    public String toString() {
        return "BscTransaction{" +
                "txHash='" + txHash + '\'' +
                ", unixTimestamp=" + unixTimestamp +
                ", dateTime=" + dateTime +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", tokenValue=" + tokenValue +
                ", usdValueDayOfTx='" + usdValueDayOfTx + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                '}';
    }
}
