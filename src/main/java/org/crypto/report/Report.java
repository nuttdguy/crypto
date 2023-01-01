package org.crypto.report;

import org.crypto.IReportService;
import org.crypto.Transaction;
import org.crypto.bsc.account.TxActionType;
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

    /* from the file contents, returns a list of transactions */
    public List<Transaction> extractTransactionsFrom(String file, Class<? extends Transaction> clazz) throws IOException {

        // read and return the file contents
        List<String> fileContents = reportService.readTransactionsFrom(file);

        String actionType = "";
        String[] clazzNameParts = clazz.getSimpleName().split("\\.");

        // determine the transaction type to create
        switch (clazzNameParts[clazzNameParts.length-1]) {
            case "TxTokenTransaction" -> actionType = TxActionType.TOKEN_TX.label;
            case "TxListTransaction" -> actionType = TxActionType.TX_LIST.label;
            case "TxInternalTransaction" -> actionType = TxActionType.TX_LIST_INTERNAL.label;
            default -> throw new RuntimeException(format("%s is not supported", clazzNameParts[clazzNameParts.length-1]));
        }

        // transform file contents into mapped entries
        List<Map<String, String>> entries = reportService.createMappedEntriesFrom(fileContents.get(0).split(","), fileContents.get(1).split("/n/r"));

        // extract the file contents from the second element
        List<Transaction> transactionList = new ArrayList<>();
        for (Map<String, String> entry : entries) {
            Transaction transaction = reportService.createTxTransaction(entry, actionType);
            transactionList.add(transaction);
        }

        // return the list of transactions
        return transactionList;
    }

    /* read from file and return contents as String elements */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        return reportService.readTransactionsFrom(file);
    }

    /* from a TransactionList, write entries to a file with _transactions.csv appended to the fileName */
    public int writeTransactionsToCsv(List<Transaction> transactionList, String fileName, boolean append) {
        return reportService.writeTransactionsToCsv(transactionList, fileName, append);
    }

    /* from a json array, extract the data into a list of transactions  */
    protected List<Transaction> createTransactionListFrom(JSONArray jsonArrayResource, String actionType) throws JSONException {
        return reportService.createTransactionListFrom(jsonArrayResource, actionType);
    }


}
