package org.crypto.bsc.account;

import java.time.LocalDateTime;

public class BscAccount {
    private String blockHash;
    private String functionName;
    private String contractAddress;
    private String methodId;
    private int transactionIndex;
    private Long confirmations;
    private int nonce;
    private LocalDateTime timeStamp;
    private String input;
    private double gasUsed;
    private boolean isError;
    private int txreceipt_status;
    private String blockNumber;
    private double gas;
    private double cumulativeGasUsed;
    private String from;
    private String to;
    private double value;
    private String hash;
    private double gasPrice;

    private BscAccount() {
        // default constructor for builder
    }

    public String extractValuesToStringForWrite() {
        return
                this.blockHash + "," +
                this.functionName + "," +
                this.contractAddress + "," +
                this.methodId + "," +
                this.transactionIndex + "," +
                this.confirmations + "," +
                this.nonce + "," +
                this.timeStamp + "," +
                this.input + "," +
                this.gasUsed + "," +
                this.isError + "," +
                this.txreceipt_status + "," +
                this.blockNumber + "," +
                this.gas + "," +
                this.cumulativeGasUsed + "," +
                this.from + "," +
                this.to + "," +
                convertToBnb(this.value) + "," +
                this.hash + "," +
                this.gasPrice;
    }

    public double convertToBnb(double value) {
        return (value / 1000000000000000000.0);
    }

    public String getFunctionName() { return functionName; }

    public String getBlockNumber() {
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

    public String getMethodId() { return methodId; }

    public static class AccountBuilder {

        private String methodId;
        private String functionName;
        private String blockNumber;
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

        public AccountBuilder withMethodId(String methodId) {
            this.methodId = methodId;
            return this;
        }

        public AccountBuilder withFunctionName(String functionName) {
            this.functionName = functionName;
            return this;
        }

        public AccountBuilder withBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public AccountBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public AccountBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public AccountBuilder withNonce(int nonce) {
            this.nonce = nonce;
            return this;
        }

        public AccountBuilder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public AccountBuilder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public AccountBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public AccountBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public AccountBuilder withValue(double value) {
            this.value = value;
            return this;
        }

        public AccountBuilder withGas(double gas) {
            this.gas = gas;
            return this;
        }

        public AccountBuilder withGasPrice(double gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public AccountBuilder withError(boolean error) {
            isError = error;
            return this;
        }

        public AccountBuilder withTxreceipt_status(int txreceipt_status) {
            this.txreceipt_status = txreceipt_status;
            return this;
        }

        public AccountBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public AccountBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public AccountBuilder withCumulativeGasUsed(double cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public AccountBuilder withGasUsed(double gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public AccountBuilder withConfirmations(Long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public BscAccount build() {
            BscAccount account = new BscAccount();
            account.functionName = this.functionName;
            account.blockNumber = this.blockNumber;
            account.timeStamp = this.timeStamp;
            account.hash = this.hash;
            account.nonce = this.nonce;
            account.blockHash = this.blockHash;
            account.transactionIndex = this.transactionIndex;
            account.from = this.from;
            account.to = this.to;
            account.value = this.value;
            account.gas = this.gas;
            account.gasPrice = this.gasPrice;
            account.isError = this.isError;
            account.txreceipt_status = this.txreceipt_status;
            account.input = this.input;
            account.contractAddress = this.contractAddress;
            account.cumulativeGasUsed = this.cumulativeGasUsed;
            account.gasUsed = this.gasUsed;
            account.confirmations = this.confirmations;
            account.methodId = this.methodId;
            return account;
        }

    }

}
