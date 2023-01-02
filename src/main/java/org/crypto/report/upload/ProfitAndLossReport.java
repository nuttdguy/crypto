package org.crypto.report.upload;

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
    private double boughtTotal;
    private double soldTotal;
    private double profitLoss;
    private double remainQty;
    private LocalDateTime reportDate;

    public String extractFieldValuesToWrite() {
        return this.boughtQty + "," +
                this.boughtTotal + "," +
                this.profitLoss + "," +
                this.remainQty + "," +
                this.reportDate + "," +
                this.soldQty + "," +
                this.soldTotal + "," +
                this.symbol;
    }

}
