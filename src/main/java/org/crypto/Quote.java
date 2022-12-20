package org.crypto;

import java.time.LocalDateTime;

public class Quote {

    public final int id;
    public final String name;
    public final String symbol;
    public final String slug;
    public final int rank;
    public final double price;
    public final double volume;
    public final double marketCap;
    public final int marketCapDominance;
    public final double circulatingSupply;
    public final double totalSupply;
    public final LocalDateTime lastUpdated;

    public Quote(int id, String name, String symbol, String slug, int rank, double price, double volume, double marketCap, int marketCapDominance, double circulatingSupply, double totalSupply, LocalDateTime lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.rank = rank;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
        this.marketCapDominance = marketCapDominance;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.lastUpdated = lastUpdated;
    }
}
