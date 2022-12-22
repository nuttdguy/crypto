package org.crypto.quote;

import org.crypto.Transaction;

import java.time.LocalDateTime;
import java.util.Arrays;

public class QuoteTransaction extends Transaction {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private boolean isActive;
    private boolean isFiat;
    private int circulatingSupply;
    private double maxSupply;
    private LocalDateTime dateAdded;
    private int numMarketPairs;
    private int rank;
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
    private int marketCapDominance;
    private double fullyDilutedMarketCap;
//    private LocalDateTime lastUpdated;
    
    private QuoteTransaction() { }
    
    public static class TransactionBuilder {
        private int id;
        private String name;
        private String symbol;
        private String slug;
        private boolean isActive;
        private boolean isFiat;
        private int circulatingSupply;
        private double maxSupply;
        private LocalDateTime dateAdded;
        private int numMarketPairs;
        private int rank;
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
        private int marketCapDominance;
        private double fullyDilutedMarketCap;

        public TransactionBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TransactionBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public TransactionBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public TransactionBuilder withActive(boolean active) {
            isActive = active;
            return this;
        }

        public TransactionBuilder withFiat(boolean fiat) {
            isFiat = fiat;
            return this;
        }

        public TransactionBuilder withCirculatingSupply(int circulatingSupply) {
            this.circulatingSupply = circulatingSupply;
            return this;
        }

        public TransactionBuilder withMaxSupply(double maxSupply) {
            this.maxSupply = maxSupply;
            return this;
        }

        public TransactionBuilder withDateAdded(LocalDateTime dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public TransactionBuilder withNumMarketPairs(int numMarketPairs) {
            this.numMarketPairs = numMarketPairs;
            return this;
        }

        public TransactionBuilder withRank(int rank) {
            this.rank = rank;
            return this;
        }

        public TransactionBuilder withLastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public TransactionBuilder withTags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public TransactionBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public TransactionBuilder withVolume24(double volume24) {
            this.volume24 = volume24;
            return this;
        }

        public TransactionBuilder withVolumeChange24(double volumeChange24) {
            this.volumeChange24 = volumeChange24;
            return this;
        }

        public TransactionBuilder withPercentChangeHr(float percentChangeHr) {
            this.percentChangeHr = percentChangeHr;
            return this;
        }

        public TransactionBuilder withPercentChange24(float percentChange24) {
            this.percentChange24 = percentChange24;
            return this;
        }

        public TransactionBuilder withPercentChangeWk(float percentChangeWk) {
            this.percentChangeWk = percentChangeWk;
            return this;
        }

        public TransactionBuilder withPercentChange30Day(float percentChange30Day) {
            this.percentChange30Day = percentChange30Day;
            return this;
        }

        public TransactionBuilder withMarketCap(double marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public TransactionBuilder withMarketCapDominance(int marketCapDominance) {
            this.marketCapDominance = marketCapDominance;
            return this;
        }

        public TransactionBuilder withFullyDilutedMarketCap(double fullyDilutedMarketCap) {
            this.fullyDilutedMarketCap = fullyDilutedMarketCap;
            return this;
        }

        public QuoteTransaction build() {
            QuoteTransaction transaction = new QuoteTransaction();
            transaction.id = this.id;
            transaction.name = this.name;
            transaction.symbol = this.symbol;
            transaction.slug = this.slug;
            transaction.isActive = this.isActive;
            transaction.isFiat = this.isFiat;
            transaction.circulatingSupply = this.circulatingSupply;
            transaction.maxSupply = this.maxSupply;
            transaction.dateAdded = this.dateAdded;
            transaction.numMarketPairs = this.numMarketPairs;
            transaction.rank = this.rank;
            transaction.lastUpdated = this.lastUpdated;
            transaction.tags = this.tags;
            transaction.price = this.price;
            transaction.volume24 = this.volume24;
            transaction.volumeChange24 = this.volumeChange24;
            transaction.percentChangeHr = this.percentChangeHr;
            transaction.percentChange24 = this.percentChange24;
            transaction.percentChangeWk = this.percentChangeWk;
            transaction.percentChange30Day = this.percentChange30Day;
            transaction.marketCap = this.marketCap;
            transaction.marketCapDominance = this.marketCapDominance;
            transaction.fullyDilutedMarketCap = this.fullyDilutedMarketCap;
            return transaction;
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

    public int getCirculatingSupply() {
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

    public int getRank() {
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

    public int getMarketCapDominance() {
        return marketCapDominance;
    }

    public double getFullyDilutedMarketCap() {
        return fullyDilutedMarketCap;
    }

    @Override
    public String toString() {
        return "QuoteTransaction{" +
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
                '}';
    }
}
