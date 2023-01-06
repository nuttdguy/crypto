package org.crypto;

import org.crypto.bsc.BscApi;
import org.crypto.bsc.account.TxActionType;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import org.crypto.report.Report;
import org.crypto.report.TradeInstanceType;
import org.crypto.report.TransactionEntry;
import org.crypto.report.dto.BscDefiTradeLabel;
import org.crypto.report.dto.BscDefiTradeTransaction;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static org.crypto.report.dto.BscDefiTradeLabel.HASH;
import static org.crypto.report.dto.BscDefiTradeLabel.VALUE;
import static org.crypto.util.DataTypeUtil.*;


public class Main {

    public static void main(String[] args) {

        // fetch quotes
//        fetchQuote();

        // uploaded files
        executeReportMethods();

    }

    public static void executeReportMethods() {
        // to implement
        Report report = new Report();

        // todo - pull info from all these files into a list of mapped entries or class objects
        String[] bscFiles = new String[]{"tokentx_transactions.csv", "txlist_transactions.csv",  "bnb_token_price_history.csv"};
        String[] keyPrefix = new String[]{"tx", "txList", "bnbPrice"};
        int isSuccess = 0;

        // extract data from csv
        if (false) {
            isSuccess = report.readFromFileAndThenWriteReports(
                    "2022_kucoin_spot_all.csv", "2022_summary_report");
        }

        String[] fields = new String[]{"txHash", "txListHash"};
        Set<String> uniqueKeySet = new HashSet<>();
        List<Map<String, String>> mappedEntries = new ArrayList<>();
        List<BscDefiTradeTransaction> bscDefiTradeTransactions = new ArrayList<>();

        if (true) {
            int i = 0;
            for (String file : bscFiles) {
                try {
                    List<String> fileContent = report.readTransactionsFrom(file);
                    mappedEntries = report.mapToTransactionsFrom(fileContent, mappedEntries, keyPrefix[i]);

                    // extract unique key sets
                    uniqueKeySet = report.extractUniqueValuesFrom(fields, mappedEntries, uniqueKeySet);


                    i++;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            //
            // merge transactions into a single record
            List<Map<String, Map<String, String>>> combinedRecords = new ArrayList<>();
            // merge map entry set; one address combines entries from tx an txlist file
            for (String hash : uniqueKeySet) {
                Map<String, String> mapEntry = new HashMap<>();
                for (Map<String, String> entry : mappedEntries) {
                    // extract from and to address
                    if (entry.get(HASH.label) == null) {
                        continue;
                    }
                    if (entry.get(HASH.label).equals(hash)) {
                        // add transaction details from both lists
                        mapEntry.put(hash, entry);
                        // then continue -> to reduce iteration
                    }
                }
                combinedRecords.add(mapEntry);
            }

            bscDefiTradeTransactions = report.createListOfTransactions(mappedEntries, TradeInstanceType.BSC_DEFI_TRADE);
//            bscDefiTradeTransactions = report.createListOfTransactions(mappedEntries, TradeInstanceType.BSC_DEFI_TRADE);

//            List<Map<String, String>> mappedEntries = report.mapToTransactionsFrom(bscTransactionList);

        }



        out.println(isSuccess);

    }

    public static void executeBscMethods() {
        // init and set config object
        BscApi bscApi = new BscApi();

        // fetch transactions for actions in
        int writeSuccess = 0;
        for (TxActionType action : TxActionType.values()) {

            // build the config object
            TxConfig txConfig = new TxConfig.Builder().build(action, API_KEY.BSC_ADDRESS);

            List<Transaction> transactionList;
            try {
                // fetch transactions for all TxActionTypes
                transactionList = bscApi.fetchTxTransactions(txConfig);
                // write the transaction to file
                writeSuccess += bscApi.writeTransactionsToCsv(transactionList, action.label, false, true );
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        out.println("write="+writeSuccess);
        List<Transaction> transactions;
        try {
            transactions = bscApi.extractTransactionsFrom("tokentx_transactions.csv", TxTokenTransaction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static List<TransactionEntry>  createTransactionEntry(List<String> columns, List<String> fieldValues) {
        // create transaction object
        List<TransactionEntry> transactionEntryList = new ArrayList<>();
        TransactionEntry entry = new TransactionEntry();

        // set the field values by incrementing the column / class declared field for every field value
        int insertColumn = 0;
        for (String value : fieldValues) {
            if (insertColumn >= columns.size()) {
                insertColumn = 0;
                transactionEntryList.add(entry);
                entry = new TransactionEntry();
            }

            String column = columns.get(insertColumn);

            if (value.equals("null") || value.isEmpty()) {
                continue;
            }

            if (column.equals("tokentxValue") && isDouble(value)) {
                entry.setTokenTxValue(toDouble(value));
            }
            else if (column.equals("tokentxFrom")) {
                entry.setTokenTxFrom(value);
            }
            else if (column.equals("tokentxTo")) {
                entry.setTokenTxTo(value);
            }
            else if (column.equals("tokentxBlockHash")) {
                entry.setTokenTxBlockHash(value);
            }
            else if (column.equals("tokentxContractAddress")) {
                entry.setTokenTxContractAddress(value);
            }
            else if (column.equals("tokentxConfirmations") && isDouble(value)) {
                entry.setTokenTxConfirmations(toDouble(value));
            }
            else if (column.equals("tokentxGasUsed") && isDouble(value)) {
                entry.setTokenTxGasUsed(toDouble(value));
            }
            else if (column.equals("tokentxGas") && isDouble(value)) {
                entry.setTokenTxGas(toDouble(value));
            }
            else if (column.equals("tokentxGasPrice") && isDouble(value)) {
                entry.setTokenTxGasPrice(toDouble(value));
            }
            else if (column.equals("tokentxTokenName")) {
                entry.setTokenTxTokenName(value);
            }
            else if (column.equals("tokentxTokenSymbol")) {
                entry.setTokenTxTokenSymbol(value);
            }
            else if (column.equals("tokenxTokenDecimal")) {
                entry.setTokenTxTokenDecimal(value);
            }
            else if (column.equals("internalTimeStamp") && isLocalDateTime(value)) {
                entry.setInternalTimeStamp(toDateTime(value));
            }
            else if (column.equals("internalBlockNumber")) {
                entry.setInternalBlockNumber(value);
            }
            else if (column.equals("internalFrom")) {
                entry.setInternalFrom(value);
            }
            else if (column.equals("internalTo")) {
                entry.setInternalTo(value);
            }
            else if (column.equals("internalValue") && isDouble(value)) {
                entry.setInternalValue(toDouble(value));
            }
            else if (column.equals("internalBlockHash")) {
                entry.setInternalBlockHash(value);
            }
            else if (column.equals("txlistBlockHash")) {
                entry.setTxListBlockHash(value);
            }
            else if (column.equals("txlistConfirmations") && isDouble(value)) {
                entry.setTxListConfirmations(toDouble(value));
            }
            else if (column.equals("txlistTimeStamp") && isLocalDateTime(value)) {
                entry.setTxListTimeStamp(toDateTime(value));
            }
            else if (column.equals("txlistBlockNumber")) {
                entry.setTxListBlockNumber(value);
            }
            else if (column.equals("txlistGasUsed") && isDouble(value)) {
                entry.setTxListGasUsed(toDouble(value));
            }
            else if (column.equals("txlistGas") && isDouble(value)) {
                entry.setTxListGas(toDouble(value));
            }
            else if (column.equals("txlistGasPrice") && isDouble(value)) {
                entry.setTxListGasPrice(toDouble(value));
            }
            else if (column.equals("txlistFrom")) {
                entry.setTxListFrom(value);
            }
            else if (column.equals("txlistTo")) {
                entry.setTxListTo(value);
            }
            else if (column.equals("txlistValue") && isDouble(value)) {
                entry.setTxListValue(toDouble(value));
            }
            else if (column.equals("txlistHash")) {
                entry.setTxListHash(value);
            }
            else if (column.contains("bnbDate")) {
                if (isLocalDateTime(value)) {
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yy HH:mm:ss", Locale.US);
//                    LocalDateTime dt = LocalDateTime.parse(value + " 00:00:00", formatter);
//                    entry.setBnbPriceDate(dt);
                    entry.setBnbPriceDate(toDateTime(value));
                }
            }
            else if (column.contains("bnbOpen") && isDouble(value)) {
                entry.setBnbPriceOpen(toDouble(value));
            }
            else if (column.contains("bnbClose") && isDouble(value)) {
                entry.setBnbPriceClose(toDouble(value));
            }
            else if (column.contains("bnbHigh") && isDouble(value)) {
                entry.setBnbPriceHigh(toDouble(value));
            }
            else if (column.contains("bnbLow") && isDouble(value)) {
                entry.setBnbPriceLow(toDouble(value));
            }
            insertColumn++;
        }
        return transactionEntryList;
    }


    public static void fetchQuote() {
        // fetch latest quotes
        CmcApi cmcApi = new CmcApi();
        QuoteConfig config = new QuoteConfig();
        List<Quote> quoteList = cmcApi.fetchLatestQuotes(config);

        // write quote results to a file
        CSVWriter quoteCSVWriter = new CSVWriter();
        try {
            quoteCSVWriter.writeToCSV("crypto_quotes.csv", quoteList, false, true);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}