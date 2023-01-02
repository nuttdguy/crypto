package org.crypto;

import org.crypto.report.upload.KucoinSpotTradeTransaction;

import java.util.Map;

import static org.crypto.report.upload.KucoinTradeLabel.*;
import static org.crypto.report.upload.KucoinTradeLabel.FEE_CURRENCY;
import static org.crypto.util.DataTypeUtil.toDateTime;
import static org.crypto.util.DataTypeUtil.toDouble;

public class ToMapper {

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
                .filledAmount(toDouble(mapEntry.get(FILLED_AMOUNT.label)))
                .filledVolume(toDouble(mapEntry.get(FILLED_VOLUME.label)))
                .filledVolumeUsdt(toDouble(mapEntry.get(FILLED_VOLUME_USDT.label)))
                .filledTime(toDateTime(mapEntry.get(FILLED_TIME.label)))
                .fee(toDouble(mapEntry.get(FEE.label)))
                .makerTaker(mapEntry.get(MAKER_TAKER.label))
                .feeCurrency(mapEntry.get(FEE_CURRENCY.label))
                .build();
    }

}
