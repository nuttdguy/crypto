package org.crypto.report.upload;

import lombok.Builder;
import org.crypto.Transaction;

import java.time.LocalDateTime;

@Builder
public class TradeTransaction extends Transaction {

    private String symbol;
    private String side;
    private String orderType;
    private double avgFilledPrice;
    private double filledAmount;
    private double filledVolume;
    private double filledVolumeUsdt;
    private LocalDateTime filledTime;
    private double fee;
    private String makerTaker;
    private String feeCurrency;



}
