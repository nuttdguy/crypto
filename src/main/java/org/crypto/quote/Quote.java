package org.crypto.quote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quote {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private boolean isActive;
    private boolean isFiat;
    private double circulatingSupply;
    private double maxSupply;
    private LocalDateTime dateAdded;
    private int numMarketPairs;
    private double rank;
    private LocalDateTime lastUpdated;
    private String[] tags;
    private double price;
    private double volume24;
    private double volumeChange24;
    private float percentChangeHr;
    private float percentChange24;
    private float percentChangeWk;
    private float percentChange30Day;
    private double marketCap;
    private double marketCapDominance;
    private double fullyDilutedMarketCap;
    private LocalDateTime lastUpdatedQuote;

    private Quote() {
    }

    public String extractFieldValuesToWrite() {

        return this.getId() + "," +
                this.getName() + "," +
                this.getSymbol() + "," +
                this.getSlug() + "," +
                this.isActive() + "," +
                this.isFiat() + "," +
                this.getCirculatingSupply() + "," +
                this.getMaxSupply() + "," +
                this.getDateAdded() + "," +
                this.getNumMarketPairs() + "," +
                this.getRank() + "," +
                this.getLastUpdated() + "," +
                tagsToString(this.tags) + "," +
                this.getPrice() + "," +
                this.getVolume24() + "," +
                this.getVolumeChange24() + "," +
                this.getPercentChangeHr() + "," +
                this.getPercentChange24() + "," +
                this.getPercentChangeWk() + "," +
                this.getPercentChange30Day() + "," +
                this.getMarketCap() + "," +
                this.getMarketCapDominance() + "," +
                this.getFullyDilutedMarketCap() + "," +
                this.getLastUpdated();
    }

    public String tagsToString(String[] array) {
        return Stream.of(array)
                .map(tag -> tag.replace(",", "::"))
                .collect(Collectors.joining(""));
    }

    public static class QuoteBuilder {
        private int id;
        private String name;
        private String symbol;
        private String slug;
        private boolean isActive;
        private boolean isFiat;
        private double circulatingSupply;
        private double maxSupply;
        private LocalDateTime dateAdded;
        private int numMarketPairs;
        private double rank;
        private LocalDateTime lastUpdated;
        private String[] tags;
        private double price;
        private double volume24;
        private double volumeChange24;
        private float percentChangeHr;
        private float percentChange24;
        private float percentChangeWk;
        private float percentChange30Day;
        private double marketCap;
        private double marketCapDominance;
        private double fullyDilutedMarketCap;
        private LocalDateTime lastUpdatedQuote;

        public QuoteBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public QuoteBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public QuoteBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public QuoteBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public QuoteBuilder withActive(boolean active) {
            isActive = active;
            return this;
        }

        public QuoteBuilder withFiat(boolean fiat) {
            isFiat = fiat;
            return this;
        }

        public QuoteBuilder withCirculatingSupply(double circulatingSupply) {
            this.circulatingSupply = circulatingSupply;
            return this;
        }

        public QuoteBuilder withMaxSupply(double maxSupply) {
            this.maxSupply = maxSupply;
            return this;
        }

        public QuoteBuilder withDateAdded(LocalDateTime dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public QuoteBuilder withNumMarketPairs(int numMarketPairs) {
            this.numMarketPairs = numMarketPairs;
            return this;
        }

        public QuoteBuilder withRank(double rank) {
            this.rank = rank;
            return this;
        }

        public QuoteBuilder withLastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public QuoteBuilder withTags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public QuoteBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public QuoteBuilder withVolume24(double volume24) {
            this.volume24 = volume24;
            return this;
        }

        public QuoteBuilder withVolumeChange24(double volumeChange24) {
            this.volumeChange24 = volumeChange24;
            return this;
        }

        public QuoteBuilder withPercentChangeHr(float percentChangeHr) {
            this.percentChangeHr = percentChangeHr;
            return this;
        }

        public QuoteBuilder withPercentChange24(float percentChange24) {
            this.percentChange24 = percentChange24;
            return this;
        }

        public QuoteBuilder withPercentChangeWk(float percentChangeWk) {
            this.percentChangeWk = percentChangeWk;
            return this;
        }

        public QuoteBuilder withPercentChange30Day(float percentChange30Day) {
            this.percentChange30Day = percentChange30Day;
            return this;
        }

        public QuoteBuilder withMarketCap(double marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public QuoteBuilder withMarketCapDominance(double marketCapDominance) {
            this.marketCapDominance = marketCapDominance;
            return this;
        }

        public QuoteBuilder withFullyDilutedMarketCap(double fullyDilutedMarketCap) {
            this.fullyDilutedMarketCap = fullyDilutedMarketCap;
            return this;
        }

        public QuoteBuilder withLastUpdatedQuote(LocalDateTime lastUpdatedQuote) {
            this.lastUpdatedQuote = lastUpdatedQuote;
            return this;
        }

        public Quote build() {
            Quote quote = new Quote();
            quote.id = this.id;
            quote.name = this.name;
            quote.symbol = this.symbol;
            quote.slug = this.slug;
            quote.isActive = this.isActive;
            quote.isFiat = this.isFiat;
            quote.circulatingSupply = this.circulatingSupply;
            quote.maxSupply = this.maxSupply;
            quote.dateAdded = this.dateAdded;
            quote.numMarketPairs = this.numMarketPairs;
            quote.rank = this.rank;
            quote.lastUpdated = this.lastUpdated;
            quote.tags = this.tags;
            quote.price = this.price;
            quote.volume24 = this.volume24;
            quote.volumeChange24 = this.volumeChange24;
            quote.percentChangeHr = this.percentChangeHr;
            quote.percentChange24 = this.percentChange24;
            quote.percentChangeWk = this.percentChangeWk;
            quote.percentChange30Day = this.percentChange30Day;
            quote.marketCap = this.marketCap;
            quote.marketCapDominance = this.marketCapDominance;
            quote.fullyDilutedMarketCap = this.fullyDilutedMarketCap;
            quote.lastUpdatedQuote = this.lastUpdatedQuote;
            return quote;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSlug() {
        return slug;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isFiat() {
        return isFiat;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public int getNumMarketPairs() {
        return numMarketPairs;
    }

    public double getRank() {
        return rank;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String[] getTags() {
        return tags;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume24() {
        return volume24;
    }

    public double getVolumeChange24() {
        return volumeChange24;
    }

    public float getPercentChangeHr() {
        return percentChangeHr;
    }

    public float getPercentChange24() {
        return percentChange24;
    }

    public float getPercentChangeWk() {
        return percentChangeWk;
    }

    public float getPercentChange30Day() {
        return percentChange30Day;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getMarketCapDominance() {
        return marketCapDominance;
    }

    public double getFullyDilutedMarketCap() {
        return fullyDilutedMarketCap;
    }

    public LocalDateTime getLastUpdatedQuote() {
        return lastUpdatedQuote;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", isActive=" + isActive +
                ", isFiat=" + isFiat +
                ", circulatingSupply=" + circulatingSupply +
                ", maxSupply=" + maxSupply +
                ", dateAdded=" + dateAdded +
                ", numMarketPairs=" + numMarketPairs +
                ", rank=" + rank +
                ", lastUpdated=" + lastUpdated +
                ", tags=" + Arrays.toString(tags) +
                ", price=" + price +
                ", volume24=" + volume24 +
                ", volumeChange24=" + volumeChange24 +
                ", percentChangeHr=" + percentChangeHr +
                ", percentChange24=" + percentChange24 +
                ", percentChangeWk=" + percentChangeWk +
                ", percentChange30Day=" + percentChange30Day +
                ", marketCap=" + marketCap +
                ", marketCapDominance=" + marketCapDominance +
                ", fullyDilutedMarketCap=" + fullyDilutedMarketCap +
                ", lastUpdatedQuote=" + lastUpdatedQuote +
                '}';
    }
}
