package org.crypto.quote;

import org.crypto.API_KEY;
import org.crypto.Config;

import java.util.Objects;

public class QuoteConfig extends Config {

    private static final String APIKEY = API_KEY.SECRET;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";
    private String resourceUrl;
    private int version;
    private int limit;
    private int start;
    private double price_min;
    private double price_max;
    private double market_cap_min;
    private double volume_24h_min;
    private double volume_24h_max;
    private double circulating_supply_min;
    private double circulating_supply_max;
    private double percent_change_24h_max;
    private String convert;
    private String convert_id;
    private String[] sort;
    private String[] sort_dir;
    private String[] cryptocurrency_type;
    private String[] tag;
    private String[] aux;

    public QuoteConfig() {
        this.resourceUrl = BASE_URL + "v1/cryptocurrency/listings/latest?limit=1000";
    }

    public String getAPIKey() {
        return APIKEY;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl, int version, int limit) {
        this.limit = limit;
        this.resourceUrl = BASE_URL + "v"+version+resourceUrl+"?limit="+limit;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getParamString() {
        String paramString = "";
        if (start != 0) { paramString += "?start="+start; }
        if (price_min != 0) { paramString += "?price_min="+price_min; }
        if (price_max != 0) { paramString += "?price_max="+price_max; }
        if (market_cap_min != 0) { paramString += "?market_cap_min="+market_cap_min; }
        if (volume_24h_min != 0) { paramString += "?volume_24h_min="+volume_24h_min; }
        if (volume_24h_max != 0) { paramString += "?volume_24h_max="+volume_24h_max; }
        if (circulating_supply_min != 0) { paramString += "?circulating_supply_min="+circulating_supply_min; }
        if (circulating_supply_max != 0) { paramString += "?circulating_supply_max="+circulating_supply_max; }
        if (percent_change_24h_max != 0) { paramString += "?percent_change_24h_max="+percent_change_24h_max; }
        if (Objects.nonNull(convert)) { paramString += "?convert="+convert; }
        if (Objects.nonNull(convert_id)) { paramString += "?convert_id="+convert_id; }
        if (sort != null && sort.length > 0) { paramString += "?sort="+ String.join(",", sort); }
        if (sort_dir != null && sort_dir.length > 0) { paramString += "?sort_dir="+ String.join(",", sort_dir); }
        if (cryptocurrency_type != null && cryptocurrency_type.length > 0) { paramString += "?cryptocurrency_type="+ String.join(",", cryptocurrency_type); }
        if (tag != null && tag.length > 0) { paramString += "?tag="+ String.join(",", tag); }
        if (aux != null && aux.length > 0) { paramString += "?aux="+ String.join(",", aux); }
        return paramString;
    }

    public static class paramBuilder {
        private int start;
        private double price_min;
        private double price_max;
        private double market_cap_min;
        private double volume_24h_min;
        private double volume_24h_max;
        private double circulating_supply_min;
        private double circulating_supply_max;
        private double percent_change_24h_max;
        private String convert;
        private String convert_id;
        private String[] sort;
        private String[] sort_dir;
        private String[] cryptocurrency_type;
        private String[] tag;
        private String[] aux;

        public paramBuilder withStart(int start) {
            this.start = start; return this;
        }

        public paramBuilder withPrice_min(double price_min) {
            this.price_min = price_min; return this;
        }

        public paramBuilder withPrice_max(double price_max) {
            this.price_max = price_max; return this;
        }

        public paramBuilder withMarket_cap_min(double market_cap_min) {
            this.market_cap_min = market_cap_min; return this;
        }

        public paramBuilder withVolume_24h_min(double volume_24h_min) {
            this.volume_24h_min = volume_24h_min; return this;
        }

        public paramBuilder withVolume_24h_max(double volume_24h_max) {
            this.volume_24h_max = volume_24h_max; return this;
        }

        public paramBuilder withCirculating_supply_min(double circulating_supply_min) {
            this.circulating_supply_min = circulating_supply_min; return this;
        }

        public paramBuilder withCirculating_supply_max(double circulating_supply_max) {
            this.circulating_supply_max = circulating_supply_max; return this;
        }

        public paramBuilder withPercent_change_24h_max(double percent_change_24h_max) {
            this.percent_change_24h_max = percent_change_24h_max; return this;
        }

        public paramBuilder withConvert(String convert) {
            this.convert = convert; return this;
        }

        public paramBuilder withConvert_id(String convert_id) {
            this.convert_id = convert_id; return this;
        }

        public paramBuilder withSort(String[] sort) {
            this.sort = sort; return this;
        }

        public paramBuilder withSort_dir(String[] sort_dir) {
            this.sort_dir = sort_dir; return this;
        }

        public paramBuilder withCryptocurrency_type(String[] cryptocurrency_type) {
            this.cryptocurrency_type = cryptocurrency_type; return this;
        }

        public paramBuilder withTag(String[] tag) {
            this.tag = tag; return this;
        }

        public paramBuilder withAux(String[] aux) {
            this.aux = aux; return this;
        }

        public QuoteConfig build() {
            QuoteConfig config = new QuoteConfig();
            config.start = this.start;
            config.price_min = this.price_min;
            config.price_max = this.price_max;
            config.market_cap_min = this.market_cap_min;
            config.volume_24h_min = this.volume_24h_min;
            config.volume_24h_max = this.volume_24h_max;
            config.circulating_supply_min = this.circulating_supply_min;
            config.circulating_supply_max = this.circulating_supply_max;
            config.percent_change_24h_max = this.percent_change_24h_max;
            config.convert = this.convert;
            config.convert_id = this.convert_id;
            config.sort = this.sort;
            config.sort_dir = this.sort_dir;
            config.cryptocurrency_type = this.cryptocurrency_type;
            config.tag = this.tag;
            config.aux = this.aux;
            return config;
        }

    }


}
