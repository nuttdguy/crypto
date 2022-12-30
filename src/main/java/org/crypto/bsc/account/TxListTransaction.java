package org.crypto.bsc.account;

import java.time.LocalDateTime;

public class TxListTransaction extends Transaction {

    private int blockNumber;
    private LocalDateTime timeStamp;
    private String hash;
    private int nonce;
    private String blockHash;
    private int transactionIndex;
    private String from;
    private String to;
    private double value;
    private double gas;
    private double gasPrice;
    private boolean isError;
    private int txreceipt_status;
    private String input;
    private String contractAddress;
    private double cumulativeGasUsed;
    private double gasUsed;
    private Long confirmations;

    private TxListTransaction() {
        // default constructor for builder
    }

    public String extractFieldValuesToWrite() {
        return this.blockNumber + "," +
                this.timeStamp + "," +
                this.hash + "," +
                this.from + "," +
                this.to + "," +
                this.value + "," +
                this.contractAddress + "," +
                this.input + "," +
                this.gas + "," +
                this.gasUsed + "," +
                this.isError + "," +
                this.confirmations;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public int getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getValue() {
        return value;
    }

    public double getGas() {
        return gas;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public boolean isError() {
        return isError;
    }

    public int getTxreceipt_status() {
        return txreceipt_status;
    }

    public String getInput() {
        return input;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public double getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public double getGasUsed() {
        return gasUsed;
    }

    public Long getConfirmations() {
        return confirmations;
    }

    public static class Builder {

        private int blockNumber;
        private LocalDateTime timeStamp;
        private String hash;
        private int nonce;
        private String blockHash;
        private int transactionIndex;
        private String from;
        private String to;
        private double value;
        private double gas;
        private double gasPrice;
        private boolean isError;
        private int txreceipt_status;
        private String input;
        private String contractAddress;
        private double cumulativeGasUsed;
        private double gasUsed;
        private Long confirmations;

        public Builder withBlockNumber(int blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public Builder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder withNonce(int nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public Builder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public Builder withValue(double value) {
            this.value = value;
            return this;
        }

        public Builder withGas(double gas) {
            this.gas = gas;
            return this;
        }

        public Builder withGasPrice(double gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public Builder withError(boolean error) {
            isError = error;
            return this;
        }

        public Builder withTxreceipt_status(int txreceipt_status) {
            this.txreceipt_status = txreceipt_status;
            return this;
        }

        public Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public Builder withCumulativeGasUsed(double cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public Builder withGasUsed(double gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public Builder withConfirmations(Long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxListTransaction build() {
            TxListTransaction txList = new TxListTransaction();
            txList.blockNumber = this.blockNumber;
            txList.timeStamp = this.timeStamp;
            txList.hash = this.hash;
            txList.nonce = this.nonce;
            txList.blockHash = this.blockHash;
            txList.transactionIndex = this.transactionIndex;
            txList.from = this.from;
            txList.to = this.to;
            txList.value = this.value;
            txList.gas = this.gas;
            txList.gasPrice = this.gasPrice;
            txList.isError = this.isError;
            txList.txreceipt_status = this.txreceipt_status;
            txList.input = this.input;
            txList.contractAddress = this.contractAddress;
            txList.cumulativeGasUsed = this.cumulativeGasUsed;
            txList.gasUsed = this.gasUsed;
            txList.confirmations = this.confirmations;
            return txList;
        }


    }
    
}
