package org.crypto;

import org.crypto.bsc.BscApi;
import org.crypto.bsc.account.Transaction;
import org.crypto.bsc.account.TxActionType;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import org.crypto.report.TransactionEntry;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;
import static org.crypto.util.DataTypeUtil.*;


public class Main {

    public static void main(String[] args) {

        // fetch quotes
//        fetchQuote();


        // generate single report file
//        String fileName = "all_bsc_transactions.csv";
//        List<String> files = List.of(
//                "txlistinternal_transactions.csv",
//                "tokentx_transactions.csv",
//                "txlist_transactions.csv",
//                "bnb_token_price_history.csv"
//        );
//
//        List<String> fileContents = new ArrayList<>();

//        try {
//            fileContents = readFromFiles(files);
//        } catch (IOException io) {
//            io.printStackTrace();
//        }



    }

    public static void fetchBsc() {
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
                writeSuccess += bscApi.writeTransactionsToCsv(transactionList, action.label, false);
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
        CSVWriter<Quote> quoteCSVWriter = new CSVWriter<>();
        try {
            quoteCSVWriter.writeToCSV("crypto_quotes.csv", quoteList, false);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}