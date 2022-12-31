package org.crypto.bsc.account;

import org.crypto.API_KEY;

import java.util.List;
import java.util.Objects;

public class TxConfig {

    private static final String APIKEY = API_KEY.BSC_SECRET;
    private static final String BASE_URL = "https://api.bscscan.com/api/";

    private final String module = "account";
    private TxActionType action;
    private String address;
    private List<String> tag;
    private String blockNo;
    private Integer startBlock;
    private Integer endBlock;
    private Integer page;
    private Integer offset;
    private String sort;

    private TxConfig() {
        // default for builder
    }

    private TxConfig(TxActionType action, String address) {
        this.action = action;
        this.address = address;
    }

    public String getResourceUrl() {
        return BASE_URL + "?module=" + module + "&action=" + action.label + "&address=" + address;
    }

    public String getParamString() {
        String paramString = "";
        if (Objects.nonNull(tag)) {
            paramString += "&tag=" + String.join(",", tag);
        }
        if (Objects.nonNull(blockNo)) {
            paramString += "&blockno=" + blockNo;
        }
        if (Objects.nonNull(startBlock)) {
            paramString += "&startblock=" + startBlock;
        }
        if (Objects.nonNull(endBlock)) {
            paramString += "&endBlock=" + endBlock;
        }
        if (Objects.nonNull(page)) {
            paramString += "&page=" + page;
        }
        if (Objects.nonNull(offset)) {
            paramString += "&sort=" + sort;
        }
        return paramString;
    }

    public String getApikey() {
        return "&apikey=" + APIKEY;
    }

    public TxActionType getAction() {
        return action;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getTag() {
        return tag;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public Integer getStartBlock() {
        return startBlock;
    }

    public Integer getEndBlock() {
        return endBlock;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getSort() {
        return sort;
    }

    public static class Builder {
        private TxActionType action;
        private String address;
        private List<String> tag;
        private String blockNo;
        private Integer startBlock;
        private Integer endBlock;
        private Integer page;
        private Integer offset;
        private String sort;


        public Builder withAction(TxActionType action) {
            this.action = action;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withTag(List<String> tag) {
            this.tag = tag;
            return this;
        }

        public Builder withBlockNo(String blockNo) {
            this.blockNo = blockNo;
            return this;
        }

        public Builder withStartBlock(Integer startBlock) {
            this.startBlock = startBlock;
            return this;
        }

        public Builder withEndBlock(Integer endBlock) {
            this.endBlock = endBlock;
            return this;
        }

        public Builder withPage(Integer page) {
            this.page = page;
            return this;
        }

        public Builder withOffset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder withSort(String sort) {
            this.sort = sort;
            return this;
        }

        public TxConfig build() {
            TxConfig txConfig = new TxConfig();
            txConfig.action = this.action;
            txConfig.address = this.address;
            txConfig.tag = this.tag;
            txConfig.blockNo = this.blockNo;
            txConfig.startBlock = this.startBlock;
            txConfig.endBlock = this.endBlock;
            txConfig.page = this.page;
            txConfig.offset = this.offset;
            txConfig.sort = this.sort;
            return txConfig;
        }

        public TxConfig build(TxActionType action, String address) {
            return new TxConfig(action, address);
        }

    }

}
