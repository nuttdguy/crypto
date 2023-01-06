package org.crypto.report;

import org.crypto.*;
import org.crypto.report.dto.KucoinSpotTradeTransaction;

import java.io.IOException;
import java.util.*;

import static java.lang.String.format;
import static org.crypto.report.TradeInstanceType.BSC_DEFI_TRADE;
import static org.crypto.report.TradeInstanceType.KUCOIN_SPOT_TRADE;


public class ReportService implements IReportService {


    @Override
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerCol, String fileContent, String fRowDelimiter, String fColDelimiter, String keyPrefix) {
        List<Map<String, String>> entries = new ArrayList<>();

        // iterate through each row entry
        for (String row : fileContent.split(fRowDelimiter)) {
            Map<String, String> entry = new HashMap<>();
            String[] content = row.split(fColDelimiter);  // split the current row into single elements

            int i = 0;
            for (String header : headerCol) {

                header = keyPrefix + Character.toUpperCase(header.charAt(0)) + header.substring(1);

                // map the header and field values for the current row
                entry.putIfAbsent(header.trim(), content[i].trim());
                i++;
            }
            // add the map entry to the list
            entries.add(entry);
        }
        return entries;
    }

    /* form a list of mapped entries, create a transaction list */
    @Override
    public <T extends Transaction> List<T> createTransactionsByFieldAndSymbol(String field, String tickerSymbol, List<Map<String, String>> mapEntries, TradeInstanceType tradeInstanceType) {
        // extract the file contents from the second element
        List<T> transactionList = new ArrayList<>();

        for (Map<String, String> entry : mapEntries) {
            String symbolValue = entry.get(field);
            if (symbolValue.equals(tickerSymbol)) {

                // create a Transaction instance from the map entry
                T transaction = (T) createTransactionInstance(entry, tradeInstanceType);
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public Set<String> extractUniqueValuesFrom(String field, List<Map<String, String>> mapEntries) {
        Set<String> hSet = new HashSet<>();
        for (Map<String, String> mapEntry : mapEntries) {
            hSet.add(mapEntry.get(field));
        }
        return hSet;
    }

    public Set<String> extractUniqueValuesFrom(String[] fields, List<Map<String, String>> mapEntries) {
        Set<String> hSet = new HashSet<>();
        for (Map<String, String> mapEntry : mapEntries) {
            for (String field : fields) {
                hSet.add(mapEntry.get(field));
            }
        }
        return hSet;
    }

    /* from a mapped entry, create a SINGLE Transaction Instance matching the instance type */
    @Override
    public Transaction createTransactionInstance(Map<String, String> mapEntry, TradeInstanceType tradeInstanceType) {

        // create a Transaction matching the action type
        // map key & value pairs into list of class instances
        if (tradeInstanceType.equals(KUCOIN_SPOT_TRADE)) {
            return ToMapper.toKucoinSpotTradeTransaction(mapEntry);
        }

        throw new RuntimeException(format("%s instance type is not supported", tradeInstanceType));
    }

    /* creates Many Transaction instances from a mapped entry  */
    @Override
    public <T extends Transaction> List<T> createTransactionInstance(List<Map<String, String>> mapEntries, TradeInstanceType tradeInstanceType) {

        List<T> transactions = new ArrayList<>();
        for (Map<String, String> mapEntry: mapEntries) {
            if (tradeInstanceType.equals(KUCOIN_SPOT_TRADE)) {
                transactions.add((T) ToMapper.toKucoinSpotTradeTransaction(mapEntry));
            } else if (tradeInstanceType.equals(BSC_DEFI_TRADE)) {
                transactions.add((T) ToMapper.toBscDefiTradeTransaction(mapEntry));
            } else {
                throw new RuntimeException(format("%s instance type is not supported", tradeInstanceType));
            }
        }

        return transactions;
    }



    @Override
    /* read from file and return a list of Transactions */
    public List<String> retrieveTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        CSVReader csvReader = new CSVReader();
        return csvReader.readFrom(file);
    }

    @Override
    public <T extends Transaction> int writeTransactionsToCsv(List<T> transactions, String fileName, boolean append, boolean includeHeader) {
        // write BscAccount results to a file
        CSVWriter csvWriter = new CSVWriter();
        String fileExt = "_transactions.csv";
        try {
            csvWriter.writeToCSV(fileName + fileExt, transactions, append, includeHeader);
            return 1;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public int writeTransactionsToCsv(List<Map<String, String>> mapEntries, String fileName, boolean append) {
        // write BscAccount results to a file
        CSVWriter csvWriter = new CSVWriter();
        String fileExt = "_transactions.csv";
        try {
            csvWriter.writeToCSV(mapEntries,fileName + fileExt,  append);
            return 1;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}
