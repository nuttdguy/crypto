package org.crypto;

import org.crypto.report.TradeInstanceType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static org.crypto.util.JsonUtil.mapObjectsToString;
import static org.crypto.util.JsonUtil.toJsonObject;

public interface IReportService {

    /* create a mapped entry from a header and file contents */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerCol, String fileContent,String fRowDelimiter, String fColDelimiter);

    /* form a list of mapped entries, create a transaction list */
    public <T extends Transaction> List<T> createTransactionsByFieldAndSymbol(String field, String tickerSymbol, List<Map<String, String>> mapEntries, TradeInstanceType tradeInstanceType);

    /* creates a Transaction class instance from a mapped entry  */
    public Transaction createTransactionInstance(Map<String, String> mapEntry, TradeInstanceType tradeInstanceType);

    /* read from file and return a list of Transactions */
    public List<String> retrieveTransactionsFrom(String file) throws IOException;

    public <T extends Transaction> int writeTransactionsToCsv(List<T> transactionList, String fileName, boolean append, boolean includeHeader);

    /* from a TransactionList, write entries to a file */
    public int writeTransactionsToCsv(List<Map<String, String>> mapEntries, String fileName, boolean append);

    public Set<String> extractUniqueValuesFrom(String field, List<Map<String, String>> mapEntries);
}
