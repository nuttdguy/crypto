package org.crypto.model;

import java.time.LocalDateTime;

public class Quote extends TokenInfo {

    private final double price;
    private final double volume24;
    private final double volumeChange24;
    private final float percentChangeHr;
    private final float percentChange24;
    private final float percentChangeWk;
    private final float percentChange30Day;
    private final double marketCap;
    private final int marketCapDominance;
    private final double fullyDilutedMarketCap;
    private final LocalDateTime lastUpdated;

    public Quote(double price,
                 double volume24,
                 double volumeChange24,
                 float percentChangeHr,
                 float percentChange24,
                 float percentChangeWk,
                 float percentChange30Day,
                 double marketCap,
                 int marketCapDominance,
                 double fullyDilutedMarketCap,
                 LocalDateTime lastUpdated) {
        this(null,
                price,
                volume24,
                volumeChange24,
                percentChangeHr,
                percentChange24,
                percentChangeWk,
                percentChange30Day,
                marketCap,
                marketCapDominance,
                fullyDilutedMarketCap,
                lastUpdated);
    }

    public Quote(TokenInfo tokenInfo,
                 double price,
                 double volume24,
                 double volumeChange24,
                 float percentChangeHr,
                 float percentChange24,
                 float percentChangeWk,
                 float percentChange30Day,
                 double marketCap,
                 int marketCapDominance,
                 double fullyDilutedMarketCap,
                 LocalDateTime lastUpdated) {
        super(tokenInfo);
        this.price = price;
        this.volume24 = volume24;
        this.volumeChange24 = volumeChange24;
        this.percentChangeHr = percentChangeHr;
        this.percentChange24 = percentChange24;
        this.percentChangeWk = percentChangeWk;
        this.percentChange30Day = percentChange30Day;
        this.marketCap = marketCap;
        this.marketCapDominance = marketCapDominance;
        this.fullyDilutedMarketCap = fullyDilutedMarketCap;
        this.lastUpdated = lastUpdated;
    }

    public Quote(TokenInfo tokenInfo,
                 double price,
                 double volume24,
                 double volumeChange24,
                 float percentChangeHr,
                 float percentChange24,
                 float percentChangeWk,
                 double marketCap,
                 int marketCapDominance,
                 double fullyDilutedMarketCap,
                 LocalDateTime lastUpdated) {
        super(tokenInfo);
        this.price = price;
        this.volume24 = volume24;
        this.volumeChange24 = volumeChange24;
        this.percentChangeHr = percentChangeHr;
        this.percentChange24 = percentChange24;
        this.percentChangeWk = percentChangeWk;
        this.marketCap = marketCap;
        this.marketCapDominance = marketCapDominance;
        this.fullyDilutedMarketCap = fullyDilutedMarketCap;
        this.lastUpdated = lastUpdated;
        this.percentChange30Day = 0;
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "price=" + price +
                ", volume24=" + volume24 +
                ", volumeChange24=" + volumeChange24 +
                ", percentChangeHr=" + percentChangeHr +
                ", percentChange24=" + percentChange24 +
                ", percentChangeWk=" + percentChangeWk +
                ", percentChange30Day=" + percentChange30Day +
                ", marketCap=" + marketCap +
                ", marketCapDominance=" + marketCapDominance +
                ", fullyDilutedMarketCap=" + fullyDilutedMarketCap +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
