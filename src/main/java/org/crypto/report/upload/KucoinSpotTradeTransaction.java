package org.crypto.report.upload;

import lombok.Builder;
import lombok.Getter;
import org.crypto.Transaction;

import java.time.LocalDateTime;

@Builder
@Getter
public class KucoinSpotTradeTransaction extends Transaction {

    private String accountType;
    private double avgFilledPrice;
    private double fee;
    private String feeCurrency;
    private double filledAmount;
    private LocalDateTime filledTime;
    private double filledVolume;
    private double filledVolumeUsdt;
    private String makerTaker;
    private String orderId;
    private String orderType;
    private String side;
    private String symbol;
    private String uid;

    public String extractFieldValuesToWrite() {
        return this.accountType + "," +
                this.avgFilledPrice + "," +
                this.fee + "," +
                this.feeCurrency + "," +
                this.filledAmount + "," +
                this.filledTime + "," +
                this.filledVolume + "," +
                this.filledVolumeUsdt + "," +
                this.makerTaker + "," +
                this.orderId + "," +
                this.orderType + "," +
                this.side + "," +
                this.symbol + "," +
                this.uid;
    }

}
