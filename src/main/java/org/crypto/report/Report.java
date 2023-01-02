package org.crypto.report;

import org.crypto.IReportService;
import org.crypto.Transaction;
import org.crypto.exchange.kucoin.ActionType;
import org.crypto.report.upload.KucoinSpotTradeTransaction;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/* class for creating reports from files or data */
public class Report {

    private final IReportService reportService = new ReportService();

    /* create a mapped entry from a header and file contents */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent) {
        return reportService.createMappedEntriesFrom(headerRow, fileContent);
    }

    /* from list of mapped entries; create a transaction list matching the symbol */
    public <T extends Transaction> List<T> createTransactionsBySymbol(List<Map<String, String>> mapEntries, String tickerSymbol, TradeSourceType tradeSourceType) {
        // extract the file contents from the second element
        List<T> transactionList = new ArrayList<>();

        for (Map<String, String> entry : mapEntries) {
            String symbolValue = entry.get("Symbol");
            if (symbolValue.equals(tickerSymbol)) {
                T transaction = (T) reportService.createTransactionInstance(entry, tradeSourceType.label);
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    /* from the file, create and return a list of mapped entries */
    public List<Map<String, String>> extractTransactionsFrom(String file) throws IOException {
        // read and return the file contents
        List<String> fileContents = reportService.readTransactionsFrom(file);
        return reportService.createMappedEntriesFrom(fileContents.get(0).split(","), fileContents.get(1).split("/n/r"));
    }





    /* read from file and return contents as String elements */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        return reportService.readTransactionsFrom(file);
    }

    /* from a TransactionList, write entries to a file with _transactions.csv appended to the fileName */
    public <T extends Transaction> int writeTransactionsToCsv(List<T> transactionList, String fileName, boolean append) {
        return reportService.writeTransactionsToCsv(transactionList, fileName, append);
    }

    /* from a json array, extract the data into a list of transactions  */
    protected List<Transaction> createTransactionListFrom(JSONArray jsonArrayResource, String actionType) throws JSONException {
        return reportService.createTransactionListFrom(jsonArrayResource, actionType);
    }


}
