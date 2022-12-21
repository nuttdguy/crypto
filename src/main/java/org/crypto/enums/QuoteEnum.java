package org.crypto.enums;

public enum QuoteEnum {
    ID("id"),
    NAME("name"),
    SYMBOL("symbol"),
    SLUG("slug"),
    IS_ACTIVE("is_active"),
    IS_FIAT("is_fiat"),
    CIRCULATING_SUPPLY("circulating_supply"),
    MAX_SUPPLY("max_supply"),
    DATE_ADDED("date_added"),
    NUM_MARKET_PAIRS("num_market_pairs"),
    CMC_RANK("cmc_rank"),
    LAST_UPDATED("last_updated"),
    TAGS("tags"),
    PRICE("price"),
    VOLUME_24("volume_24h"),
    VOLUME_CHANGE_24("volume_change_24h"),
    PERCENT_CHANGE_HR("percent_change_1h"),
    PERCENT_CHANGE_24("percent_change_24h"),
    PERCENT_CHANGE_7_DAY("percent_change_7d"),
    PERCENT_CHANGE_30_DAY("percent_change_30d"),
    MARKET_CAP("market_cap"),
    MARKET_CAP_DOMINANCE("market_cap_dominance"),
    FULLY_DILUTED_MARKET_CAP("fully_diluted_market_cap");

    public final String label;

    private QuoteEnum(String label) {
        this.label = label;
    }
}
