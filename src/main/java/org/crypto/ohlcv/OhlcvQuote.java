package org.crypto.ohlcv;

import java.time.LocalDateTime;

public class OhlcvQuote extends Ohlcv {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final double marketCap;
    private final LocalDateTime timestamp;
    private final LocalDateTime timeOpen;
    private final LocalDateTime timeClose;
    private final LocalDateTime timeHigh;
    private final LocalDateTime timeLow;

    public OhlcvQuote(Ohlcv ohlcv,
                 double open,
                 double high,
                 double low,
                 double close,
                 double volume,
                 double marketCap,
                 LocalDateTime timestamp,
                 LocalDateTime timeOpen,
                 LocalDateTime timeClose,
                 LocalDateTime timeHigh,
                 LocalDateTime timeLow) {
        super(ohlcv.getId(), ohlcv.getName(), ohlcv.getSymbol());
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.marketCap = marketCap;
        this.timestamp = timestamp;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.timeHigh = timeHigh;
        this.timeLow = timeLow;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LocalDateTime getTimeOpen() {
        return timeOpen;
    }

    public LocalDateTime getTimeClose() {
        return timeClose;
    }

    public LocalDateTime getTimeHigh() {
        return timeHigh;
    }

    public LocalDateTime getTimeLow() {
        return timeLow;
    }
}
