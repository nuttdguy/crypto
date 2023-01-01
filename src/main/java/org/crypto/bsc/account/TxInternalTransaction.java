package org.crypto.bsc.account;

import org.crypto.Transaction;

import java.time.LocalDateTime;

public class TxInternalTransaction extends Transaction {

    private int blockNumber;
    private LocalDateTime timeStamp;
    private String hash;
    private String from;
    private String to;
    private double value;
    private String contractAddress;
    private String input;
    private String type;
    private double gas;
    private double gasUsed;
    private String traceId;
    private boolean isError;
    private String errCode;
    
    private TxInternalTransaction() { 
        // default builder
    }

    public String extractFieldValuesToWrite() {
        return
                this.blockNumber + "," +
                this.contractAddress + "," +
                this.errCode + "," +
                this.from + "," +
                this.gas + "," +
                this.gasUsed + "," +
                this.hash + "," +
                this.input + "," +
                this.isError + "," +
                this.timeStamp + "," +
                this.to + "," +
                this.traceId + "," +
                this.type + "," +
                this.value;
    }

    public static class Builder {

        int blockNumber;
        LocalDateTime timeStamp;
        String hash;
        String from;
        String to;
        double value;
        String contractAddress;
        String input;
        String type;
        int gas;
        int gasUsed;
        String traceId;
        boolean isError;
        String errCode;

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

        public Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withGas(int gas) {
            this.gas = gas;
            return this;
        }

        public Builder withGasUsed(int gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public Builder withTraceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder withError(boolean error) {
            isError = error;
            return this;
        }

        public Builder withErrCode(String errCode) {
            this.errCode = errCode;
            return this;
        }

        public TxInternalTransaction build() {
            TxInternalTransaction txInternal = new TxInternalTransaction();
            txInternal.blockNumber = this.blockNumber;
            txInternal.timeStamp = this.timeStamp;
            txInternal.hash = this.hash;
            txInternal.from = this.from;
            txInternal.to = this.to;
            txInternal.value = this.value;
            txInternal.contractAddress = this.contractAddress;
            txInternal.input = this.input;
            txInternal.type = this.type;
            txInternal.gas = this.gas;
            txInternal.gasUsed = this.gasUsed;
            txInternal.traceId = this.traceId;
            txInternal.isError = this.isError;
            txInternal.errCode = this.errCode;
            return txInternal;
        }
    }
    
}
