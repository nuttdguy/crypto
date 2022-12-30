package org.crypto.bsc;

import org.crypto.bsc.account.Transaction;

import java.time.LocalDateTime;

public class BscTransaction extends Transaction {

    private String txHash;
    private long unixTimestamp;
    private LocalDateTime dateTime;
    private String from;
    private String to;
    private double tokenValue;
    private double usdValueDayOfTx;
    private String contractAddress;
    private String tokenName;
    private String tokenSymbol;

    BscTransaction() { }

    public static class TransactionBuilder {
        private String txHash;
        private long unixTimestamp;
        private LocalDateTime dateTime;
        private String from;
        private String to;
        private double tokenValue;
        private double usdValueDayOfTx;
        private String contractAddress;
        private String tokenName;
        private String tokenSymbol;

        public TransactionBuilder withTxHash(String txHash) {
            this.txHash = txHash;
            return this;
        }

        public TransactionBuilder withUnixTimestamp(long unixTimestamp) {
            this.unixTimestamp = unixTimestamp;
            return this;
        }

        public TransactionBuilder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public TransactionBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TransactionBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public TransactionBuilder withTokenValue(double tokenValue) {
            this.tokenValue = tokenValue;
            return this;
        }

        public TransactionBuilder withUsdValueDayOfTx(double usdValueDayOfTx) {
            this.usdValueDayOfTx = usdValueDayOfTx;
            return this;
        }

        public TransactionBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TransactionBuilder withTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public TransactionBuilder withTokenSymbol(String tokenSymbol) {
            this.tokenSymbol = tokenSymbol;
            return this;
        }

        public BscTransaction build() {
            BscTransaction transaction = new BscTransaction();
            transaction.txHash = this.txHash;
            transaction.unixTimestamp = this.unixTimestamp;
            transaction.dateTime = this.dateTime;
            transaction.from = this.from;
            transaction.to = this.to;
            transaction.tokenValue = this.tokenValue;
            transaction.usdValueDayOfTx = this.usdValueDayOfTx;
            transaction.contractAddress = this.contractAddress;
            transaction.tokenName = this.tokenName;
            transaction.tokenSymbol = this.tokenSymbol;
            return transaction;
        }

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
