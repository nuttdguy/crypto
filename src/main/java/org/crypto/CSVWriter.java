package org.crypto;

import org.crypto.bsc.account.TxInternalTransaction;
import org.crypto.bsc.account.TxListTransaction;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.report.TransactionEntry;
import org.crypto.report.upload.KucoinSpotTradeTransaction;
import org.crypto.report.upload.ProfitAndLossReport;

import java.io.*;
import java.util.*;

public class CSVWriter {

    public CSVWriter() {
        // nothing to implement
    }

    public List<String> extractMemberFieldsOf(Class<?> clazz, boolean sort) {
        // extract and sort the header row columns
        List<String> fieldList = ClassFieldExtractor.extractDeclaredFields(clazz);
        if (sort)
            Collections.sort(fieldList);
        return fieldList;
    }


    /* Write */
    public <T extends Transaction> void writeToCSV(String fileName, List<T> transactionList, boolean append, boolean includeHeader) throws IOException {

        // return when list is empty
        if (transactionList.isEmpty()) { return; }

        // get the current directory, append to the filename
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        // write the file the current user dir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName, append))) {

            // include the header row ?
            if (includeHeader) {
                // extract member fields of the class to use as header row
                String fields = String.join(",", extractMemberFieldsOf(transactionList.get(0).getClass(), true));
                writer.write(fields);
                writer.newLine();
            }

            for (T transaction : transactionList) {

                String dataToWrite = ToMapper.extractFieldValuesToWrite(transaction);
                writer.write(dataToWrite);
                writer.newLine();
            }
        }
    }


    public void writeToCSV(List<Map<String, String>> mapEntries, String fileName, boolean append) throws IOException {
        // get the current directory, append to the filename
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;


        // write the file the current user dir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName, append))) {

            for (Map<String, String> entry : mapEntries) {

                writer.write(entry.toString() + ",");
                writer.newLine();
            }

        }
    }


    /* extract and merge the content from multiple files by row  */
    public List<String> mergeCSVIntoMappedEntries(List<String> files) {
        // store file data into a single string
        List<String> fileContent = new ArrayList<>();

        // extract the header fields
        String headerRowFields = "";
        for (String file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String[] headerFields = reader.readLine().split(",");
                String headerPrefix = file.split("_")[0];
                int i = 0;
                for (String header : headerFields) {
                    String hName = header.replaceFirst(String.valueOf(header.charAt(0)), String.valueOf(header.charAt(0)).toUpperCase());
                    String headName = headerPrefix + hName.trim();
                    headerRowFields = (i == headerFields.length-1) ?
                            headerRowFields + headName : headerRowFields + headName + ",";
                    i++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            headerRowFields += ":ENDFILE";
        }

        // remove trailing comma
        fileContent.add(headerRowFields);

        StringBuilder sb = new StringBuilder();
        for (String file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                // append contents of file by row
                String line;
                boolean skipFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (skipFirstLine) { skipFirstLine = false; continue; }
                    sb.append(line).append(":LINE_BREAK");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileContent.add(sb.toString());
            sb = new StringBuilder();
        }

        return fileContent;

    }


}