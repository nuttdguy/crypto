package org.crypto;

import org.crypto.bsc.BscApi;
import org.crypto.bsc.account.TxActionType;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import org.crypto.report.Report;
import org.crypto.report.TradeSourceType;
import org.crypto.report.TransactionEntry;
import org.crypto.report.upload.KucoinSpotTradeTransaction;
import org.crypto.report.upload.ProfitAndLossReport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.out;
import static org.crypto.report.TradeSourceType.KUCOIN_SPOT_TRADE;
import static org.crypto.util.DataTypeUtil.*;


public class Main {

    public static void main(String[] args) {

        // fetch quotes
//        fetchQuote();

        // uploaded files
        executeReportMethods();


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

    public static void executeReportMethods() {
        // to implement
        Report report = new Report();

        // extract data from csv
        List<Map<String, String>> entries; // return a list of mapped entries
        List<KucoinSpotTradeTransaction> transactionList = new ArrayList<>();  // return a list of transaction objects for the requested token symbol
        Map<String, String> profitLossReport = new HashMap<>();
//        String[] symbolsToExtract = new String[]{"ADA3L-USDT, ADA3S-USDT"}
        try {

            // returns mapped entries for every row in the file
            entries = report.extractTransactionsFrom("2022_kucoin_spot_all.csv");

            // insert option loc 1 :: calculate and add additional key value pairs

            // create list for a single symbol - or use for loop to iterate through every unique symbol found
            transactionList = report.createTransactionsBySymbol(entries, "ADA3L-USDT", KUCOIN_SPOT_TRADE);

            // insert option loc 2 : OR calculate and add additional key value pairs here with class to handle report

            profitLossReport.put("Symbol", transactionList.get(0).getSymbol());

            profitLossReport.put("BoughtQty", String.valueOf(transactionList
                    .stream()
                    .filter(entry -> entry.getSide().equals("BUY"))
                    .map(KucoinSpotTradeTransaction::getFilledAmount)
                    .reduce(0.00, Double::sum)));

            profitLossReport.put("SoldQty", String.valueOf(transactionList
                    .stream()
                    .filter(entry -> entry.getSide().equals("SELL"))
                    .map(KucoinSpotTradeTransaction::getFilledAmount)
                    .reduce(0.00, Double::sum)));

            profitLossReport.put("BoughtTotal", String.valueOf(transactionList
                    .stream()
                    .filter(entry -> entry.getSide().equals("BUY"))
                    .map(KucoinSpotTradeTransaction::getFilledVolumeUsdt)
                    .reduce(0.00, Double::sum)));

            profitLossReport.put("SoldTotal", String.valueOf(transactionList
                    .stream()
                    .filter(entry -> entry.getSide().equals("SELL"))
                    .map(KucoinSpotTradeTransaction::getFilledVolumeUsdt)
                    .reduce(0.00, Double::sum)));

            profitLossReport.put("ProfitLoss", String.valueOf(
                    toDouble(profitLossReport.get("SoldTotal")) - toDouble(profitLossReport.get("BoughtTotal"))));

            profitLossReport.put("RemainQty", String.valueOf(
                    toDouble(profitLossReport.get("BoughtQty")) - toDouble(profitLossReport.get("SoldQty"))));

            profitLossReport.put("ReportDate", LocalDateTime.now().toString());

            List<ProfitAndLossReport> plReports = List.of(ProfitAndLossReport.builder()
                            .symbol(profitLossReport.get("Symbol"))
                            .boughtQty(toDouble(profitLossReport.get("BoughtQty")))
                            .boughtTotal(toDouble(profitLossReport.get("BoughtTotal")))
                            .soldQty(toDouble(profitLossReport.get("SoldQty")))
                            .soldTotal(toDouble(profitLossReport.get("SoldTotal")))
                            .profitLoss(toDouble(profitLossReport.get("ProfitLoss")))
                            .remainQty(toDouble(profitLossReport.get("RemainQty")))
                            .reportDate(toDateTime(profitLossReport.get("ReportDate")))
                            .build()
            );

            // write transactions to csv
            report.writeTransactionsToCsv(transactionList, transactionList.get(0).getSymbol().toLowerCase(), false);
            report.writeTransactionsToCsv(plReports, plReports.get(0).getSymbol().toLowerCase()+"_report", false);


            out.println(transactionList);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // aggregate and consolidate results, group by token symbol


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