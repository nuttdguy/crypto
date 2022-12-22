package org.crypto.quote;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Quote {

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

    public Quote() {}

    public Quote(Quote quote) {
        this(quote.id,
                quote.name,
                quote.symbol,
                quote.slug,
                quote.isActive,
                quote.isFiat,
                quote.circulatingSupply,
                quote.maxSupply,
                quote.dateAdded,
                quote.numMarketPairs,
                quote.rank,
                quote.lastUpdated,
                quote.tags);
    }

    public Quote(int id,
                 String name,
                 String symbol,
                 String slug,
                 boolean isActive,
                 boolean isFiat,
                 int circulatingSupply,
                 double maxSupply,
                 LocalDateTime dateAdded,
                 int numMarketPairs,
                 int rank,
                 LocalDateTime lastUpdated,
                 String[] tags) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.isActive = isActive;
        this.isFiat = isFiat;
        this.circulatingSupply = circulatingSupply;
        this.maxSupply = maxSupply;
        this.dateAdded = dateAdded;
        this.numMarketPairs = numMarketPairs;
        this.rank = rank;
        this.lastUpdated = lastUpdated;
        this.tags = tags;
    }

    public Quote(int id,
                 String name,
                 String symbol,
                 String slug,
                 int rank,
                 int numMarketPairs,
                 int circulatingSupply,
                 double maxSupply,
                 LocalDateTime lastUpdated,
                 LocalDateTime dateAdded,
                 String[] tags) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.circulatingSupply = circulatingSupply;
        this.maxSupply = maxSupply;
        this.dateAdded = dateAdded;
        this.numMarketPairs = numMarketPairs;
        this.rank = rank;
        this.lastUpdated = lastUpdated;
        this.tags = tags;
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
                '}';
    }
}
