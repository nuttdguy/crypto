package org.crypto.quote;

import java.time.LocalDateTime;

public class QuoteDetailBuilder {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private boolean isActive;
    private boolean isFiat;
    private int circulatingSupply;
    private int maxSupply;
    private LocalDateTime dateAdded;
    private int numMarketPairs;
    private int rank;
    private LocalDateTime lastUpdated;
    private String[] tags;

    public QuoteDetailBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public QuoteDetailBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public QuoteDetailBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public QuoteDetailBuilder setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public QuoteDetailBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public QuoteDetailBuilder setIsFiat(boolean isFiat) {
        this.isFiat = isFiat;
        return this;
    }

    public QuoteDetailBuilder setCirculatingSupply(int circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
        return this;
    }

    public QuoteDetailBuilder setMaxSupply(int maxSupply) {
        this.maxSupply = maxSupply;
        return this;
    }

    public QuoteDetailBuilder setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public QuoteDetailBuilder setNumMarketPairs(int numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
        return this;
    }

    public QuoteDetailBuilder setRank(int rank) {
        this.rank = rank;
        return this;
    }

    public QuoteDetailBuilder setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public QuoteDetailBuilder setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public Quote createTokenInfo() {
        return new Quote(id, name, symbol, slug, isActive, isFiat, circulatingSupply, maxSupply, dateAdded, numMarketPairs, rank, lastUpdated, tags);
    }
}