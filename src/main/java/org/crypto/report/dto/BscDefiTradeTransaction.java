package org.crypto.report.dto;

import lombok.Builder;
import lombok.Getter;
import org.crypto.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
/* dto class for transferring bsc-explorer mapped entries */
public class BscDefiTradeTransaction extends Transaction {

    // these 3 members should be the same from the two spreadsheets
    // use hashing func for 3 pairs as key OR use the hash to pair transactions - which will always be unique per transaction
    private String hash; // use hash as key since its unique
    private int nonce;
    private int blockNumber;

    // from token tx (contains the info for token exchanges)
    private String txTokenContractAddress;
    private String txTokenFrom;
    private String txTokenTo;
    private String txTokenName;
    private String txTokenSymbol;
    private int txTokenDecimal;
    private double txTokenValue;
    private double txTokenGas;
    private double txTokenGasPrice;
    private double txTokenGasUsed;

    // from txlist (contains the info to bnb exchanges
    private LocalDateTime txListTimeStamp;
    private String txListFrom;
    private String txListTo;
    private double txListValue;
    private double txListGas;
    private double txListGasPrice;
    private double txListGasUsed;

    // for bnb price
    private LocalDateTime bnbPriceDate;
    private BigDecimal bnbPriceOpen;
    private BigDecimal bnbPriceClose;
    private BigDecimal bnbPriceHigh;
    private BigDecimal bnbPriceLow;



//** BUYS == TO ADDRESS
//** SELLS == FROM ADDRESS

//	+ 0x36805ea6a184b816362ba5bf93cf936ee42ae6f4728bef3c04774ed15811d084  (hash)
//            + 0x9130990dd16ed8be8be63e46cad305c2c339dac9 (contract address)
//
//            + 0x0ebf5ee50997922620847eec472bad35ca02105d (from)
//            + 0x1c93ba02fcf68fd9bee9e1a15a21495beaf36ad5 (to)
//
//            + 4,587,949,950,000,000 / 9 (tokenDecimal) (value)
//            + 9 (tokenDecimal)
//            + 11123000 (blockNumber)
//            + 20 (nonce)
//
//            -- pair by hash and from address (txlist)
//	+ 0x36805ea6a184b816362ba5bf93cf936ee42ae6f4728bef3c04774ed15811d084 (hash)
//            + 11123000 (blockNumber)
//            + 2022-12-31T08:03:23.493026 (timestamp)
//
//            + 0x1c93ba02fcf68fd9bee9e1a15a21495beaf36ad5 (from)
//            + 0x10ed43c718714eb63d5aa57b78b54704e256024e (to)
//
//            + 1,608,895,777,793,860,000.00 / 1000000000000000000 (value)/(wei)
//            + 20 (nonce)
//
//            -- bnb price (bnb_price_history)
//	+ 19-Dec-22 (date)
//            + 251.24 (open)
//            + 240.66 (close)
//            + 252.93 (high)
//            + 238.65 (low)

}
