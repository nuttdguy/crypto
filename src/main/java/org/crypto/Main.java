package org.crypto;

import org.crypto.bsc.BscApi;
import org.crypto.bsc.account.Transaction;
import org.crypto.bsc.account.TxConfig;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import org.crypto.report.TransactionEntry;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.out;
import static org.crypto.util.DataTypeUtil.*;


public class Main {

    public static void main(String[] args) {

        // fetch quotes
//        fetchQuote();

        // fetch bsc account for module=account:
        // not implemented actions ["tokennfttx", "getminedblocks"]
        // mot implemented modules ["contracts", "transactions", "blocks", "tokens"]
        String[] txActions = new String[]{"txlist", "txlistinternal", "tokentx"};

        // todo -- fix the generic attempt to model data; n=does not work b/c fields that should not be included are.
//        fetchBscAccount("tokentx");

        for (String action : txActions) {
            fetchBscAccount(action);
        }

        // generate single report file
        String fileName = "all_bsc_transactions.csv";
        List<String> files = List.of(
                "internal_bsc_account.csv",
                "tokentx_bsc_account.csv",
                "txlist_bsc_account.csv",
                "bnb_price_history.csv"
        );

        List<String> fileContents = new ArrayList<>();

        try {
            fileContents = readFromFiles(files);
        } catch (IOException io) {
            io.printStackTrace();
        }

//        List<TokenTx> tokenTxList = new ArrayList<>();
//        for (String fieldValues : fileContents.get(3).split(":ENDLINE")) {
//            TokenTx tokenTx = toTokenTx(fieldValues.split(","));
//            tokenTxList.add(tokenTx);
//        }


        // redo
//        generateReport(files, fileName);


    }

    /* read from multiple files; return header row and file content as list elements for each file */
    public static List<String> readFromFiles(List<String> files) throws IOException {
        // get file contents as a list
        CSVReader<TransactionEntry> csvReader = new CSVReader<>();
        return csvReader.readFromCSV(files);
    }

//    public static InternalBsc toInternalBsc(String[] values) {
//        return new InternalBsc.Builder()
//                .withBlockNumber(toInteger(values[0]))
//                .withTimeStamp(toDateTime(values[1]))
//                .withHash(values[2])
//                .withFrom(values[3])
//                .withTo(values[4])
//                .withValue(toInteger(values[5]))
//                .withContractAddress(values[6])
//                .withInput(values[7])
//                .withType(values[8])
//                .withGas(toInteger(values[9]))
//                .withGasUsed(toInteger(values[10]))
//                .withTraceId(values[11])
//                .withError(toBoolean(values[12]))
//                .withErrCode(values[13])
//                .build();
//    }


    /* create tokenTx object from string array */
//    public static TokenTx toTokenTx(String[] values) {
//
//        return new TokenTx.Builder()
//            .withBlockHash(values[0])
//            .withConfirmations(toInteger(values[1]))
//            .withContractAddress(values[2])
//            .withFunctionName(values[3])
//            .withInput(values[4])
//            .withError(toBoolean(values[5]))
//            .withMethodId(values[6])
//            .withBlockNumber(toInteger(values[7]))
//            .withTransactionIndex(toInteger(values[8]))
//            .withNonce(toInteger(values[9]))
//            .withTimeStamp(toDateTime(values[10]))
//            .withGasUsed(toInteger(values[11]))
//            .withTxreceipt_status(toInteger(values[12]))
//            .withGas(toInteger(values[13]))
//            .withCumulativeGasUsed(toInteger(values[14]))
//            .withFrom(values[15])
//            .withTo(values[16])
//            .withValue(toDouble(values[17]))
//            .withHash(values[18])
//            .withGasPrice(toDouble(values[19]))
//            .withTokenName(values[20])
//            .withTokenSymbol(values[21])
//            .withTokenDecimal(toInteger(values[22]))
//            .build();
//
//    }


    public static void generateReport(List<String> files, String fileName) {

        // write BscAccount results to a file
        CSVWriter<TransactionEntry> quoteCSVWriter = new CSVWriter<>();

        // 1. use row # as key, concat to value for every row, i.e. 1:file-1-row1 + file2-row1 + file3-row-1
//        Map<Integer, String> mappedEntries = new HashMap<>();
        List<String> mappedEntries = new ArrayList<>();

        try {
            // get the mapped entries from all the files
//            mappedEntries = quoteCSVWriter.mergeCSVIntoMappedEntries(files);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // 2. extract the header fields into a list
        // split and then add each header column field
//        List<String> headerFieldList = Arrays.asList(mappedEntries.get(0)
//                .replace("/", "")
//                .replace(" ", "")
//                .split(","));
//        mappedEntries.remove(0);

        // 3. create transaction entries for every record
        // for every entry, set the field value
        List<TransactionEntry> transactionEntries = new ArrayList<>();

        String[] headerContent = mappedEntries.get(0).split(":ENDFILE");
        String[] file1Content = mappedEntries.get(1).split(":LINE_BREAK");
        String[] file2Content = mappedEntries.get(2).split(":LINE_BREAK");
        String[] file3Content = mappedEntries.get(3).split(":LINE_BREAK");
        String[] file4Content = mappedEntries.get(4).split(":LINE_BREAK");

        Map<Integer, String> finalContent = new HashMap<>();
        StringBuilder line = new StringBuilder();

        int headerColumnSetItr = 0;
        int headerColumnLen = headerContent[0].split(",").length;
        for (int k = 0; k < headerColumnLen; k++) {
            String temp = finalContent.getOrDefault(headerColumnSetItr, "");
            line.append(temp).append(",").append(file1Content[k]);
            headerColumnSetItr++;

        }
        headerColumnSetItr = 0;
        headerColumnLen = headerContent[1].split(",").length;
        for (int k = 0; k < headerColumnLen; k++) {
            String temp = finalContent.getOrDefault(headerColumnSetItr, "");
            line.append(temp).append(",").append(file2Content[k]);
            headerColumnSetItr++;
        }
        headerColumnSetItr = 0;
        headerColumnLen = headerContent[2].split(",").length;
        for (int k = 0; k < headerColumnLen; k++) {
            String temp = finalContent.getOrDefault(headerColumnSetItr, "");
            line.append(temp).append(",").append(file3Content[k]);
            headerColumnSetItr++;
        }
        headerColumnSetItr = 0;
        headerColumnLen = headerContent[3].split(",").length;
        for (int k = 0; k < headerColumnLen; k++) {
            String temp = finalContent.getOrDefault(headerColumnSetItr, "");
            line.append(temp).append(",").append(file4Content[k]);
            headerColumnSetItr++;
        }

        Map<String, String> mapOfRowData = new HashMap<>();
        String[] columnData = line.toString().split(",");

        int idx = 0;
        List<String> headerFields = Arrays.stream(headerContent).flatMap(ele -> Stream.of(ele.split(","))).toList();
        for (String header : headerFields) {
            String data = columnData[idx];
            mapOfRowData.put(header, data);
            idx++;
        }

        TransactionEntry transactionEntry = new TransactionEntry();
        for (String value : mapOfRowData.values()) {

            if (mapOfRowData.containsKey("tokentxValue")) {
                transactionEntry.setTokenTxValue(toDouble(value));
            }
            if (mapOfRowData.containsKey("tokentxFrom")) {
                transactionEntry.setTokenTxFrom(value);
            }
            if (mapOfRowData.containsKey("tokentxTo")) {
                transactionEntry.setTokenTxTo(value);
            }
            if (mapOfRowData.containsKey("tokentxBlockHash")) {
                transactionEntry.setTokenTxBlockHash(value);
            }
            if (mapOfRowData.containsKey("tokentxContractAddress")) {
                transactionEntry.setTokenTxContractAddress(value);
            }
            if (mapOfRowData.containsKey("tokentxConfirmations")) {
                transactionEntry.setTokenTxConfirmations(toDouble(value));
            }
            if (mapOfRowData.containsKey("tokentxGasUsed")) {
                transactionEntry.setTokenTxGasUsed(toDouble(value));
            }
            if (mapOfRowData.containsKey("tokentxGas")) {
                transactionEntry.setTokenTxGas(toDouble(value));
            }
            if (mapOfRowData.containsKey("tokentxGasPrice") ) {
                transactionEntry.setTokenTxGasPrice(toDouble(value));
            }
            if (mapOfRowData.containsKey("tokentxTokenName")) {
                transactionEntry.setTokenTxTokenName(value);
            }
            if (mapOfRowData.containsKey("tokentxTokenSymbol")) {
                transactionEntry.setTokenTxTokenSymbol(value);
            }
            if (mapOfRowData.containsKey("tokenxTokenDecimal")) {
                transactionEntry.setTokenTxTokenDecimal(value);
            }
            if (mapOfRowData.containsKey("internalTimeStamp") && isLocalDateTime(value)) {
                transactionEntry.setInternalTimeStamp(toDateTime(value));
            }
            if (mapOfRowData.containsKey("internalBlockNumber")) {
                transactionEntry.setInternalBlockNumber(value);
            }
            if (mapOfRowData.containsKey("internalFrom")) {
                transactionEntry.setInternalFrom(value);
            }
            if (mapOfRowData.containsKey("internalTo")) {
                transactionEntry.setInternalTo(value);
            }
            if (mapOfRowData.containsKey("internalValue") ) {
                transactionEntry.setInternalValue(toDouble(value));
            }
            if (mapOfRowData.containsKey("internalBlockHash")) {
                transactionEntry.setInternalBlockHash(value);
            }
            if (mapOfRowData.containsKey("txlistBlockHash")) {
                transactionEntry.setTxListBlockHash(value);
            }
            if (mapOfRowData.containsKey("txlistConfirmations") ) {
                transactionEntry.setTxListConfirmations(toDouble(value));
            }
            if (mapOfRowData.containsKey("txlistTimeStamp") && isLocalDateTime(value)) {
                transactionEntry.setTxListTimeStamp(toDateTime(value));
            }
            if (mapOfRowData.containsKey("txlistBlockNumber")) {
                transactionEntry.setTxListBlockNumber(value);
            }
            if (mapOfRowData.containsKey("txlistGasUsed") ) {
                transactionEntry.setTxListGasUsed(toDouble(value));
            }
            if (mapOfRowData.containsKey("txlistGas") ) {
                transactionEntry.setTxListGas(toDouble(value));
            }
            if (mapOfRowData.containsKey("txlistGasPrice") ) {
                transactionEntry.setTxListGasPrice(toDouble(value));
            }
            if (mapOfRowData.containsKey("txlistFrom")) {
                transactionEntry.setTxListFrom(value);
            }
            if (mapOfRowData.containsKey("txlistTo")) {
                transactionEntry.setTxListTo(value);
            }
            if (mapOfRowData.containsKey("txlistValue") ) {
                transactionEntry.setTxListValue(toDouble(value));
            }
            if (mapOfRowData.containsKey("txlistHash")) {
                transactionEntry.setTxListHash(value);
            }
            if (mapOfRowData.containsKey("bnbDate")) {
                if (isLocalDateTime(value)) {
                    transactionEntry.setBnbPriceDate(toDateTime(value));
                }
            }
            if (mapOfRowData.containsKey("bnbOpen") && isDouble(value)) {
                transactionEntry.setBnbPriceOpen(toDouble(value));
            }
            if (mapOfRowData.containsKey("bnbClose") && isDouble(value)) {
                transactionEntry.setBnbPriceClose(toDouble(value));
            }
            if (mapOfRowData.containsKey("bnbHigh") && isDouble(value)) {
                transactionEntry.setBnbPriceHigh(toDouble(value));
            }
            if (mapOfRowData.containsKey("bnbLow") && isDouble(value)) {
                transactionEntry.setBnbPriceLow(toDouble(value));
            }
            // create a transaction entry; then add to list
        }
        transactionEntries.add(transactionEntry);


//        finalContent.put(0, line.toString());

//        for (int k = 0; k < headerFieldList.size(); k++) {
////            String temp = finalContent.getOrDefault(headerColumnSetItr, "");
//            finalContent.put(k, line.toString());
//        }

//        finalContent.add(line.toString());
//        line = new StringBuilder();
//        headerColumnSetItr = 0;

//
//        for (int k = 0; k < headerContent[headerColumnSetItr].length(); k++) {
//
//            line.append(file1Content[k]).append(",");
//            line.append(file2Content[k]).append(",");
//            line.append(file3Content[k]).append(",");
//            line.append(file4Content[k]).append(",");
//
////            finalContent.add(line.toString());
//            line = new StringBuilder();
//        }

//        for (int i = 0; i < file1Content.length; i++) {
//            String headerField = "", content1 = "", content2 = "", content3 = "", content4 = "";
//            content1 = file1Content[i].substring(i, i*columns) + ",";
//            content2 = file2Content[i].substring(i, i*columns)  + ",";
//            content3 = file3Content[i].substring(i, i*columns)  + ",";
//            content4 = file4Content[i].substring(i, i*columns);
//            line.append(content1).append(content2).append(content3).append(content4);
//            finalContent.add(line.toString());
//            columnNumber++;
//
//            line = new StringBuilder();
//        }




//        for (Map.Entry<Integer, String> entry : mappedEntries.entrySet()) {
//
//            // skip header row
//            if (entry.getKey() == 0) { continue; }
//
//            // split the field values into single elements - do not split comma's found in numbers
//            String[] fieldValues = entry.getValue().split(",");
//
//            // create a transaction entry; then add to list
//            transactionEntries.add(createTransactionEntry(headerFieldList, fieldValues));
//
//        }

//        Map<String, String> transactionEntries = new HashMap<>();
////        String[] fileContentParts = fileContent.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
////        String[] fileContentParts = fileContent.toString().split(",");
//        String[] headerRowParts = headerRowFields.split(",");
//
//        int columnsWidth = headerRowParts.length;
//        int colIndex = 0;
//        for (String content : fileContent) {
//            if (colIndex >= columnsWidth) {
//                columnsWidth = 0;
//            }
//            String[] fileContentParts = content.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//
        //                    String formattedLine = "";
//                    for (String part : lineParts) {
//                        part = part.replace("\"", "")
//                                .replace("$", "")
//                                .replace(",", "")
//                                .trim();
//                        formattedLine += part + ",";
//                    }
//            transactionEntries.put(headerRowParts[colIndex], fileContentParts[colIndex]);
//            colIndex++;
//        }



        try {
            quoteCSVWriter.writeToCSV("all_transactions.csv", transactionEntries, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        out.println(transactionEntries);
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

    public static void fetchBscAccount(String txAction) {

        // fetch latest quotes
        BscApi bscApi = new BscApi();
        TxConfig config = new TxConfig
                .AccountConfigBuilder()
                .build(txAction, API_KEY.BSC_ADDRESS);

        List<Transaction> accountEntries = new ArrayList<>();
        JSONArray jsonArrayResource;
        try (InputStream apiStream = bscApi.fetchBscAccount(config)) {
            // create a list from the returned resource stream
            jsonArrayResource = bscApi.extractJsonArrayFrom(apiStream, "result");
            accountEntries = bscApi.createTransactionListFrom(jsonArrayResource,  config.getAction());

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        String fileName = "";
        String postfix = "bsc_account.csv";
        if (!(accountEntries.isEmpty())) {

            if (config.getAction().equals("tokentx")) {
                fileName = "tokentx_";
            } else if (config.getAction().equals("txlistinternal")) {
                fileName = "internal_";
            } else if (config.getAction().equals("txlist")) {
                fileName = "txlist_";
            }
            // write account entries to a csv file
            toCsvFrom(accountEntries, fileName + postfix);

        }


    }

    public static void toCsvFrom(List<Transaction> accountEntries, String fileName) {

        // write BscAccount results to a file
        CSVWriter<Transaction> quoteCSVWriter = new CSVWriter<>();
        try {
            quoteCSVWriter.writeToCSV(fileName, accountEntries, false);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

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