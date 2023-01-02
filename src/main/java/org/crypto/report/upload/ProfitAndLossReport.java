package org.crypto.report.upload;

import lombok.Builder;
import lombok.Getter;
import org.crypto.Transaction;

@Builder
@Getter
public class ProfitAndLossReport extends Transaction {

    private double boughtQty;
    private double soldQty;
    private double boughtTotal;
    private double soldTotal;
    private double pl;
    private double remainQty;



}
