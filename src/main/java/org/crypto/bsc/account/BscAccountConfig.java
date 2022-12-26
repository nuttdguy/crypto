package org.crypto.bsc.account;

import org.crypto.API_KEY;

import java.util.List;
import java.util.Objects;

public class BscAccountConfig {

    enum ACTION {
        BALANCE(), BALANCE_MULTI(), BALANCE_HISTORY(), TX_LIST(), TX_LIST_INTERNAL(), TOKEN_TX()
    }

    private static final String APIKEY = API_KEY.BSC_SECRET;
    private static final String BASE_URL = "https://api.bscscan.com/api/";

    private final String module = "account";
    private String action = "txlist";
    private String address;
    private List<String> tag;
    private String blockNo;
    private Integer startBlock;
    private Integer endBlock;
    private Integer page;
    private Integer offset;
    private String sort;

    private BscAccountConfig() {
        // default for builder
    }

    private BscAccountConfig(String action, String address) {
        this.action = action;
        this.address = address;
    }

    public String getResourceUrl() {
        return BASE_URL + "?module=" + module + "&action=" + action + "&address=" + address;
    }

    public String getParamString() {
        String paramString = "";
        if (Objects.nonNull(tag)) { paramString += "&tag="+ String.join(",", tag); }
        if (Objects.nonNull(blockNo)) { paramString += "&blockno="+blockNo; }
        if (Objects.nonNull(startBlock)) { paramString += "&startblock="+startBlock; }
        if (Objects.nonNull(endBlock)) { paramString += "&endBlock="+endBlock; }
        if (Objects.nonNull(page)) { paramString += "&page="+page; }
        if (Objects.nonNull(offset)) { paramString += "&sort="+sort; }
        return paramString;
    }

    public String getApikey() {
        return "&apikey=" + APIKEY;
    }

    public String getAction() {
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

    public static class AccountConfigBuilder {
        private String action;
        private String address;
        private List<String> tag;
        private String blockNo;
        private Integer startBlock;
        private Integer endBlock;
        private Integer page;
        private Integer offset;
        private String sort;


        public AccountConfigBuilder withAction(String action) {
            this.action = action; return this;
        }

        public AccountConfigBuilder withAddress(String address) {
            this.address = address; return this;
        }

        public AccountConfigBuilder withTag(List<String> tag) {
            this.tag = tag; return this;
        }

        public AccountConfigBuilder withBlockNo(String blockNo) {
            this.blockNo = blockNo; return this;
        }

        public AccountConfigBuilder withStartBlock(Integer startBlock) {
            this.startBlock = startBlock; return this;
        }

        public AccountConfigBuilder withEndBlock(Integer endBlock) {
            this.endBlock = endBlock; return this;
        }

        public AccountConfigBuilder withPage(Integer page) {
            this.page = page; return this;
        }

        public AccountConfigBuilder withOffset(Integer offset) {
            this.offset = offset; return this;
        }

        public AccountConfigBuilder withSort(String sort) {
            this.sort = sort; return this;
        }

        public BscAccountConfig build() {
            BscAccountConfig bscAccountConfig = new BscAccountConfig();
            bscAccountConfig.action = this.action;
            bscAccountConfig.address = this.address;
            bscAccountConfig.tag = this.tag;
            bscAccountConfig.blockNo = this.blockNo;
            bscAccountConfig.startBlock = this.startBlock;
            bscAccountConfig.endBlock = this.endBlock;
            bscAccountConfig.page = this.page;
            bscAccountConfig.offset = this.offset;
            bscAccountConfig.sort = this.sort;
            return bscAccountConfig;
        }

        public BscAccountConfig build(String action, String address) {
            return new BscAccountConfig(action, address);
        }

    }

}
