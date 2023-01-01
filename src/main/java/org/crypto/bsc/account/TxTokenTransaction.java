package org.crypto.bsc.account;


import org.crypto.Transaction;

public class TxTokenTransaction extends Transaction {

    private int blockNumber;
    private String hash;
    private String from;
    private String to;
    private double value;
    private String contractAddress;
    private String input;
    private double gas;
    private double gasUsed;
    private int nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private int tokenDecimal;
    private int transactionIndex;
    private double gasPrice;
    private int cumulativeGasUsed;
    private int confirmations;

    private TxTokenTransaction() {
        // for builder
    }

    public String extractFieldValuesToWrite() {
        return
                this.blockHash + "," +
                this.blockNumber + "," +
                this.confirmations + "," +
                this.contractAddress + "," +
                this.cumulativeGasUsed + "," +
                this.from + "," +
                this.gas + "," +
                this.gasPrice + "," +
                this.gasUsed + "," +
                this.hash + "," +
                this.input + "," +
                this.nonce + "," +
                this.to + "," +
                this.tokenDecimal + "," +
                this.tokenName + "," +
                this.tokenSymbol + "," +
                this.transactionIndex + "," +
                this.value;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public String getHash() {
        return hash;
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

    public String getContractAddress() {
        return contractAddress;
    }

    public String getInput() {
        return input;
    }

    public double getGas() {
        return gas;
    }

    public double getGasUsed() {
        return gasUsed;
    }

    public int getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public int getTokenDecimal() {
        return tokenDecimal;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public int getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public static class Builder {

        private int blockNumber;
        private String hash;
        private String from;
        private String to;
        private double value;
        private String contractAddress;
        private String input;
        private double gas;
        private double gasUsed;
        private int nonce;
        private String blockHash;
        private String tokenName;
        private String tokenSymbol;
        private int tokenDecimal;
        private int transactionIndex;
        private double gasPrice;
        private int cumulativeGasUsed;
        private int confirmations;

        public Builder withBlockNumber(int blockNumber) {
            this.blockNumber = blockNumber;
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

        public Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public Builder withValue(long value) {
            this.value = value;
            return this;
        }

        public Builder withTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public Builder withTokenSymbol(String tokenSymbol) {
            this.tokenSymbol = tokenSymbol;
            return this;
        }

        public Builder withTokenDecimal(int tokenDecimal) {
            this.tokenDecimal = tokenDecimal;
            return this;
        }

        public Builder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public Builder withGas(int gas) {
            this.gas = gas;
            return this;
        }

        public Builder withGasPrice(double gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public Builder withGasUsed(int gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public Builder withCumulativeGasUsed(int cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public Builder withConfirmations(int confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxTokenTransaction build() {
            TxTokenTransaction txToken = new TxTokenTransaction();
            txToken.blockNumber = this.blockNumber;
            txToken.hash = this.hash;
            txToken.blockHash = this.blockHash;
            txToken.contractAddress = this.contractAddress;
            txToken.transactionIndex = this.transactionIndex;
            txToken.confirmations = this.confirmations;
            txToken.nonce = this.nonce;
            txToken.input = this.input;
            txToken.gasUsed = this.gasUsed;
            txToken.gas = this.gas;
            txToken.cumulativeGasUsed = this.cumulativeGasUsed;
            txToken.from = this.from;
            txToken.to = this.to;
            txToken.value = this.value;
            txToken.gasPrice = this.gasPrice;
            txToken.tokenName = this.tokenName;
            txToken.tokenSymbol = this.tokenSymbol;
            txToken.tokenDecimal = this.tokenDecimal;
            return txToken;
        }

    }

}
