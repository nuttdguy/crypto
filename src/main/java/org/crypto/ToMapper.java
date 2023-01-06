package org.crypto;

import org.crypto.bsc.account.TxInternalTransaction;
import org.crypto.bsc.account.TxListTransaction;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.report.TransactionEntry;
import org.crypto.report.dto.BscDefiTradeLabel;
import org.crypto.report.dto.BscDefiTradeTransaction;
import org.crypto.report.dto.KucoinSpotTradeTransaction;
import org.crypto.report.dto.ProfitAndLossReport;

import java.util.Map;

import static org.crypto.report.dto.BscDefiTradeLabel.*;
import static org.crypto.report.dto.KucoinTradeLabel.*;
import static org.crypto.report.dto.KucoinTradeLabel.FEE_CURRENCY;
import static org.crypto.util.DataTypeUtil.*;

public class ToMapper {


    public static <T> String extractFieldValuesToWrite(T transaction) {
        // get the class name and fields to use as header row
        String clazzName = transaction.getClass().getName();
        String dataToWrite = "";

        if (clazzName.contains("Quote")) {
            dataToWrite = ((Quote) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("TxInternalTransaction")) {
            dataToWrite = ((TxInternalTransaction) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("TxListTransaction")) {
            dataToWrite = ((TxListTransaction) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("TxTokenTransaction")) {
            dataToWrite = ((TxTokenTransaction) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("TransactionEntry")) {
            dataToWrite = ((TransactionEntry) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("KucoinSpotTradeTransaction")) {
            dataToWrite = ((KucoinSpotTradeTransaction) transaction).extractFieldValuesToWrite();
        } else if (clazzName.contains("ProfitAndLossReport")) {
            dataToWrite = ((ProfitAndLossReport) transaction).extractFieldValuesToWrite();
        }
        return dataToWrite;

    }

    /* for creating a profit and loss transaction record */


    /* for creating a Kucoin spot transaction */
    public static KucoinSpotTradeTransaction toKucoinSpotTradeTransaction(Map<String, String> mapEntry) {
        return KucoinSpotTradeTransaction.builder()
                .uid(mapEntry.get(UID.label))
                .accountType(mapEntry.get(ACCOUNT_TYPE.label))
                .orderId(mapEntry.get(ORDER_ID.label))
                .symbol(mapEntry.get(SYMBOL.label))
                .side(mapEntry.get(SIDE.label))
                .orderType(mapEntry.get(ORDER_TYPE.label))
                .avgFilledPrice(toDouble(mapEntry.get(AVG_FILLED_PRICE.label)))
                .filledQty(toDouble(mapEntry.get(FILLED_AMOUNT.label)))
                .filledVolume(toDouble(mapEntry.get(FILLED_VOLUME.label)))
                .filledVolumeUsdt(toDouble(mapEntry.get(FILLED_VOLUME_USDT.label)))
                .filledTime(toDateTime(mapEntry.get(FILLED_TIME.label)))
                .fee(toDouble(mapEntry.get(FEE.label)))
                .makerTaker(mapEntry.get(MAKER_TAKER.label))
                .feeCurrency(mapEntry.get(FEE_CURRENCY.label))
                .build();
    }

    /* for creating a Bsc defi transaction */
    public static BscDefiTradeTransaction toBscDefiTradeTransaction(Map<String, String> mapEntry) {
        return BscDefiTradeTransaction.builder()
                .hash(mapEntry.get(HASH.label))
                .nonce(toInteger(mapEntry.get(NONCE.label)))
                .blockNumber(toInteger(mapEntry.get(BLOCK_NUMBER.label)))
                .txTokenContractAddress(mapEntry.get(CONTRACT_ADDRESS.label))
                .txTokenFrom(mapEntry.get(FROM.label))
                .txTokenTo(mapEntry.get(TO.label))
                .txTokenName(mapEntry.get(TOKEN_NAME.label))
                .txTokenSymbol(mapEntry.get(TOKEN_SYMBOL.label))
                .txTokenDecimal(toInteger(mapEntry.get(TOKEN_DECIMAL.label)))
                .txTokenValue(toDouble(mapEntry.get(VALUE.label)))
                .txTokenGas(toDouble(mapEntry.get(GAS.label)))
                .txTokenGasPrice(toDouble(mapEntry.get(GAS_PRICE.label)))
                .txTokenGasUsed(toDouble(mapEntry.get(GAS_USED.label)))
                .build();


    }

}
