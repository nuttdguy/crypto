package org.crypto.report.dto;

import lombok.Builder;
import lombok.Getter;
import org.crypto.Transaction;

import java.time.LocalDateTime;

@Builder
@Getter
public class ProfitAndLossReport extends Transaction {

    private String symbol;
    private double boughtQty;
    private double soldQty;
    private double boughtAmount;
    private double soldAmount;
    private double profitLoss;
    private double remainQty;
    private LocalDateTime reportDate;

    public String extractFieldValuesToWrite() {
        return this.boughtQty + "," +
                this.boughtAmount + "," +
                this.profitLoss + "," +
                this.remainQty + "," +
                this.reportDate + "," +
                this.soldQty + "," +
                this.soldAmount + "," +
                this.symbol;
    }

}
