package org.crypto.report;

import org.checkerframework.checker.guieffect.qual.UI;
import org.crypto.*;
import org.crypto.report.upload.KucoinSpotTradeTransaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.crypto.bsc.account.TxActionType.*;
import static org.crypto.report.TradeSourceType.KUCOIN_FUTURE_TRADE;
import static org.crypto.report.TradeSourceType.KUCOIN_SPOT_TRADE;
import static org.crypto.report.upload.KucoinTradeLabel.*;
import static org.crypto.util.DataTypeUtil.toDateTime;
import static org.crypto.util.DataTypeUtil.toDouble;


public class ReportService implements IReportService {


    @Override
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent) {
        List<Map<String, String>> entries = new ArrayList<>();
        String last = fileContent[fileContent.length-1];

        // iterate through each row entry
        for (String row : fileContent) {
            Map<String, String> entry = new HashMap<>();
            String[] content = row.split(",");  // split the current row into single elements

            int i = 0;
            for (String header : headerRow) {

                // map the header and field values for the current row
                entry.putIfAbsent(header.trim(), content[i].trim());
                i++;
            }
            // add the map entry to the list
            entries.add(entry);
        }
        return entries;
    }

    @Override
    public Transaction createTransactionInstance(JSONObject jsonObject, String actionType) {
        return null;
    }

    @Override
    public Transaction createTransactionInstance(Map<String, String> mapEntry, String actionType) {

        // create a Transaction matching the action type
        // map key & value pairs into list of class instances
        if (actionType.equals(KUCOIN_SPOT_TRADE.label)) {
            return ToMapper.toKucoinSpotTradeTransaction(mapEntry);
        }

        throw new RuntimeException(format("%s action type is not supported", actionType));
    }

    @Override
    public List<Transaction> createTransactionListFrom(JSONArray resourceArray, String actionType) {
        return null;
    }

    @Override
    public InputStream fetchApiResource(String resourceUrl, String resourceParams, String APIKEY) throws IOException {
        return null;
    }

    @Override
    public JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceObjectKey) throws JSONException, IOException {
        return null;
    }

    @Override
    /* read from file and return a list of Transactions */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        CSVReader csvReader = new CSVReader();
        return csvReader.readFrom(file);
    }

//    @Override
    public <T extends Transaction> int writeTransactionsToCsv(List<T> transactions, String fileName, boolean append) {
        // write BscAccount results to a file
        CSVWriter<T> csvWriter = new CSVWriter<>();
        String fileExt = "_transactions.csv";
        try {
            csvWriter.writeToCSV(fileName + fileExt, transactions, append);
            return 1;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }


}
