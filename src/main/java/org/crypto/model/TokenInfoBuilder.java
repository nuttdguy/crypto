package org.crypto.model;

import java.time.LocalDateTime;

public class TokenInfoBuilder {
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

    public TokenInfoBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TokenInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TokenInfoBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public TokenInfoBuilder setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public TokenInfoBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public TokenInfoBuilder setIsFiat(boolean isFiat) {
        this.isFiat = isFiat;
        return this;
    }

    public TokenInfoBuilder setCirculatingSupply(int circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
        return this;
    }

    public TokenInfoBuilder setMaxSupply(int maxSupply) {
        this.maxSupply = maxSupply;
        return this;
    }

    public TokenInfoBuilder setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public TokenInfoBuilder setNumMarketPairs(int numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
        return this;
    }

    public TokenInfoBuilder setRank(int rank) {
        this.rank = rank;
        return this;
    }

    public TokenInfoBuilder setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public TokenInfoBuilder setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public TokenInfo createTokenInfo() {
        return new TokenInfo(id, name, symbol, slug, isActive, isFiat, circulatingSupply, maxSupply, dateAdded, numMarketPairs, rank, lastUpdated, tags);
    }
}