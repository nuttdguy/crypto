package org.crypto.report;

import org.crypto.ClassFieldExtractor;
import org.crypto.IReportService;
import org.crypto.Transaction;
import org.crypto.report.dto.KucoinSpotTradeTransaction;
import org.crypto.report.dto.ProfitAndLossReport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.String.format;
import static org.crypto.report.TradeInstanceType.KUCOIN_SPOT_TRADE;

/* class for creating reports from files or data */
public class Report {

    private final IReportService reportService = new ReportService();

    /* from an array of header row and file content elements, create a list mapped elements
    *  headerCol: an array of elements to represent the keys
    *  fileContent: a string of the file contents separated by a fColDelimiter of N elements equal to N headerCol elements
    *               e.g. 7 header row elements should match / equal 1 String element having 7 fColDelimiters, e.g. comma for every file content row
    *  fRowDelimiter: the character used to separate each line of file content
    *  fColDelimiter: the character used to separate each column in a file content row
    *               e.g. [row1]=a,b,c/n/r[row2]=a,b,c
    * */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerCol, String fileContent, String fRowDelimiter, String fColDelimiter, String keyPrefix) {
        return reportService.createMappedEntriesFrom(headerCol, fileContent, fRowDelimiter, fColDelimiter, keyPrefix);
    }


    /* from list of mapped entries; extracts and creates a list of transactions by field and the matching symbol
    *  field: the field key to filter, e.g. "Symbol"
    *  tickerSymbol: the symbol or parameter to include, e.g. "ADA-USDT"
    *  mappedEntries: list of mapped entries having fields(key) and values(value),
    *                 e.g. [element1]=Symbol->ADA-USDT,Gas->21000..., [element2]=Symbol->AVAX-USDT,Gas->21000,...
    *  tradeInstanceType: the type of class instance to expect, e.g. any subclass extending Transaction
    *  */
    public <T extends Transaction> List<T> createListOfTransactionsMatchingFieldAndSymbol(String field,
                                                                                          String tickerSymbol,
                                                                                          List<Map<String, String>> mapEntries,
                                                                                          TradeInstanceType tradeInstanceType) {
        return reportService.createTransactionsByFieldAndSymbol(field, tickerSymbol, mapEntries, tradeInstanceType);
    }

    /* from list of mapped entries; extracts and creates a list of transactions by field and the matching symbol
     *  mappedEntries: list of mapped entries having fields(key) and values(value),
     *                 e.g. [element1]=Symbol->ADA-USDT,Gas->21000..., [element2]=Symbol->AVAX-USDT,Gas->21000,...
     *  tradeInstanceType: the type of class instance to expect, e.g. any subclass extending Transaction
     *  */
    public <T extends Transaction> List<T> createListOfTransactions(List<Map<String, String>> mapEntries, TradeInstanceType tradeInstanceType) {
        return reportService.createTransactionInstance(mapEntries, tradeInstanceType);
    }

    /* from the file, create and return a list of mapped entries
    *  from the mappedFileContents, the first element is mapped to the values of the second content row
    *  e.g. Map.Entry :: element0header0=element1Value0, element0header0=element1Value0,  */
    public List<Map<String, String>> mapToTransactionsFrom(List<String> mappedFileContents) {
        if (mappedFileContents.size() != 2) {
            throw new RuntimeException(format("List must contain exactly 2 elements, but found %s", mappedFileContents.size()));
        }
        return reportService.createMappedEntriesFrom(mappedFileContents.get(0).split(","), mappedFileContents.get(1), "/n/r", ",", "");
    }

    /* from the file, create and return a list of mapped entries and add all to an existing list of mapped entries
     *  from the mappedFileContents, the first element is mapped to the values of the second content row
     *  e.g. Map.Entry :: element0header0=element1Value0, element0header0=element1Value0,
     * */
    public List<Map<String, String>> mapToTransactionsFrom(List<String> mappedFileContents, List<Map<String, String>> existingEntries) {
        if (mappedFileContents.size() != 2) {
            throw new RuntimeException(format("List must contain exactly 2 elements, but found %s", mappedFileContents.size()));
        }
        List<Map<String, String>> entries = reportService
                .createMappedEntriesFrom(mappedFileContents.get(0).split(","), mappedFileContents.get(1), "/n/r", ",", "");
        existingEntries.addAll(entries);
        return existingEntries;
    }

    /* from the file, create and return a list of mapped entries and add all to an existing list of mapped entries
     *  from the mappedFileContents, the first element is mapped to the values of the second content row
     *  e.g. Map.Entry :: element0header0=element1Value0, element0header0=element1Value0,
     * */
    public List<Map<String, String>> mapToTransactionsFrom(List<String> mappedFileContents, List<Map<String, String>> existingEntries, String keyPrefix) {
        if (mappedFileContents.size() != 2) {
            throw new RuntimeException(format("List must contain exactly 2 elements, but found %s", mappedFileContents.size()));
        }
        List<Map<String, String>> entries = reportService
                .createMappedEntriesFrom(mappedFileContents.get(0).split(","), mappedFileContents.get(1), "/n/r", ",", keyPrefix);
        existingEntries.addAll(entries);
        return existingEntries;
    }



    /*  the file is read and the returned as list having 2 elements
     *     the first element contains the header row, i.e. first row
     *     while the second element contains a comma separated string with each row terminated by string pattern "/n/r"  */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        return reportService.retrieveTransactionsFrom(file);
    }

    /* from a list of mapped entries, extract the unique values of the field parameter
    *   e.g. field=Symbol, extracts all unique symbols from the symbols field, e.g. ada3l-usdt, ada-usdt, etc.
    * field: the field / key to extract the unique values from
    * mapEntries: a list of mapped entries
    * */
    public Set<String> extractUniqueValuesFrom(String field, List<Map<String, String>> mapEntries) {
        return reportService.extractUniqueValuesFrom(field, mapEntries);
    }


    /* from a list of mapped entries, extract the unique values of the fields parameter and add results to an existing set
     *   e.g. fields=["Symbol", "Nonce", ...], extracts all unique values from fields
     * field: the fields / keys to extract the unique values from
     * mapEntries: a list of mapped entries
     * */
    public Set<String> extractUniqueValuesFrom(String[] fields, List<Map<String, String>> mapEntries, Set<String> existingSet) {
        Set<String> hSet = reportService.extractUniqueValuesFrom(fields, mapEntries);
//        if (hSet.size() > 1 && !hSet.contains(null))
        existingSet.addAll(hSet);
        return existingSet;
    }

    /* program method to read from a file, then generate 2 reports for each symbol
    *  desc: when a supported file is passed into this method, this method produces reports for all the unique symbols
    *       report 1: generates a report showing all buys and sells for each ticker symbol having an output file name [symbol]_transaction.csv
    *                    e.g. ada3s-usdt_transactions
    *       report 2: generates a summary report showing the accumulated buys, sells, profit and loss having file name [symbol]_report_transaction.csv
    *                   e.g. ada3s-usdt_report_transactions
    *       report 3: generates a report showing report 2 summaries in a single csv file having the summaryOutputFileName
    *   toReadFileName: name of the csv file to read
    *   summaryOutputFileName: file name for the summary report for all symbols
    * . */
    public int readFromFileAndThenWriteReports(String toReadFileName, String summaryOutputFileName) {
        List<Map<String, String>> entries; // return a list of mapped entries
        List<KucoinSpotTradeTransaction> transactionList = new ArrayList<>();  // return a list of transaction objects for the requested token symbol
        double boughtTotalAccum = 0.0, soldTotalAccum = 0.0, profitLossAccum = 0.0;
        boolean includeHeader = true;
        final String FIELD = "Symbol";

        try {

            // returns mapped entries for every row in the file
            entries = mapToTransactionsFrom(readTransactionsFrom(toReadFileName));

            // extract the unique values for key set Symbol
            Set<String> tickerSymbolSet = extractUniqueValuesFrom(FIELD, entries);

            // iterate through unique ticker symbol set and generate a report for each symbol
            for (String tickerSymbol : tickerSymbolSet) {

                // create list for a single symbol - or use for loop to iterate through every unique symbol found
                transactionList = createListOfTransactionsMatchingFieldAndSymbol(FIELD, tickerSymbol, entries, KUCOIN_SPOT_TRADE);

                // create a profit and loss report for the current ticker symbol
                ProfitAndLossReport plReport = createProfitLossReportFrom(transactionList);

                // track accumulative values for all ticker symbols
                boughtTotalAccum += plReport.getBoughtAmount();
                soldTotalAccum += plReport.getSoldAmount();
                profitLossAccum += plReport.getProfitLoss();

                // write the current transactionList to csv file
                writeTransactionsToCsv(transactionList, transactionList.get(0).getSymbol().toLowerCase(), false, true);

                // write profit and loss summaries to csv, include header for first iteration and generate new
                if (includeHeader) {
                    writeTransactionsToCsv(List.of(plReport), summaryOutputFileName, false, true);
                    includeHeader = false;
                } else {
                    // append summary reports for subsequent ticker symbols
                    writeTransactionsToCsv(List.of(plReport), summaryOutputFileName, true, false);
                }
            }

            // add final accumulative values to the report
            Map<String, String> summary = createProfitAndLossSummary(boughtTotalAccum, soldTotalAccum, profitLossAccum);
            writeTransactionsToCsv(List.of(summary), summaryOutputFileName, true);


        } catch (IOException ex) {
            ex.printStackTrace();
            return -1; // return -1 if an exception is caught
        }

        // return 1 when write operation was successful
        return 1;
    }

    private double sumFilledQty(List<KucoinSpotTradeTransaction> transactionList, MarketAction marketAction) {
        return transactionList.stream()
                .filter(entry -> entry.getSide().equals(marketAction.label))
                .mapToDouble(KucoinSpotTradeTransaction::getFilledQty)
                .sum();
    }

    private double sumFilledAmountUsdt(List<KucoinSpotTradeTransaction> transactionList, MarketAction marketAction) {
        return transactionList.stream()
                .filter(entry -> entry.getSide().equals(marketAction.label))
                .mapToDouble(KucoinSpotTradeTransaction::getFilledQty)
                .sum();
    }

    private double subtract(double sell, double buy) {
        return sell - buy;
    }

    /* sums the transactions and generates a general profit and loss report */
    public ProfitAndLossReport createProfitLossReportFrom(List<KucoinSpotTradeTransaction> transactionList) {

        // extract the fields list
        List<String> fieldList = ClassFieldExtractor.extractDeclaredFields(transactionList.get(0).getClass());
        Collections.sort(fieldList);

        // sum the qty and amount fields; this requires ordered debits and credits
        double bQty = 0.0, bAmount = 0.0, sQty = 0.0, sAmount = 0.0;
        for (String field : fieldList) {
            if (field.equals("boughtQty"))
                bQty = sumFilledQty(transactionList, MarketAction.BUY);
            else if (field.equals("soldQty"))
                sQty = sumFilledQty(transactionList, MarketAction.SELL);
            else if (field.equals("boughtAmount"))
                bAmount = sumFilledAmountUsdt(transactionList, MarketAction.BUY);
            else if (field.equals("soldAmount"))
                sAmount = sumFilledAmountUsdt(transactionList, MarketAction.SELL);

        }

        return ProfitAndLossReport.builder()
                .symbol(transactionList.get(0).getSymbol()) // get symbol from any element of the list
                .boughtQty(bQty).boughtAmount(bAmount)
                .soldQty(sQty).soldAmount(sAmount)
                .profitLoss(subtract(sAmount, bAmount))
                .remainQty(subtract(sQty, bQty))
                .reportDate(LocalDateTime.now())
                .build();
    }

    /* create a map entry for the accumulated buy, sell, profit of all symbols */
    protected Map<String, String> createProfitAndLossSummary(double boughtAmountAccum, double soldAmountAccum, double profitLossAccum) {
        Map<String, String> accumSummary = new HashMap<>();
        accumSummary.put("BoughtAmountAccum", String.valueOf(boughtAmountAccum));
        accumSummary.put("SoldAmountAccum", String.valueOf(soldAmountAccum));
        accumSummary.put("ProfitLossAccum", String.valueOf(profitLossAccum));
        return accumSummary;
    }

    // BSC REPORT METHODS
    public List<Map<String, String>> extractFieldAndValuesForReport() {

        return null;
    }




    /* from a TransactionList, write the entries to a file with _transactions.csv appended to the fileName */
    public <T extends Transaction> int writeTransactionsToCsv(List<T> transactionList, String fileName, boolean append, boolean includeHeader) {
        return reportService.writeTransactionsToCsv(transactionList, fileName, append, includeHeader);
    }

    /* from a list of Mapped Entries, write entries to a file with _transactions.csv appended to the fileName */
    public int writeTransactionsToCsv(List<Map<String, String>> mapEntries, String fileName, boolean append) {
        return reportService.writeTransactionsToCsv(mapEntries, fileName, append);
    }
}
