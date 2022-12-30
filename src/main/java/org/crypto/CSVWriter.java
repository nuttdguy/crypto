package org.crypto;

import org.crypto.bsc.account.TxInternalTransaction;
import org.crypto.bsc.account.TxListTransaction;
import org.crypto.bsc.account.TxTokenTransaction;
import org.crypto.quote.Quote;
import org.crypto.report.TransactionEntry;

import java.io.*;
import java.util.*;

public class CSVWriter<T> {

    public CSVWriter() {
        // nothing to implement
    }

    /* Write */
    public void writeToCSV(String fileName, List<T> clazzList,  boolean append) throws IOException {

        // return when list is empty
        if (clazzList.isEmpty()) { return; }

        // get the class name and fields to use as header row
        String clazzName = clazzList.get(0).getClass().getName();
        List<String> fieldList = ClassFieldExtractor.extractDeclaredFields(clazzList.get(0).getClass());
        String fields = String.join(",", fieldList);

        // get the current directory, append to the filename
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        // write the file the current user dir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName, append))) {

            writer.write(fields);
            writer.newLine();
            for (T clazz : clazzList) {
                String dataToWrite = "";

                if (clazzName.contains("Quote")) {
                    dataToWrite = ((Quote) clazz).extractFieldValuesToWrite();
                } else if (clazzName.contains("TxInternalTransaction")) {
                    dataToWrite = ((TxInternalTransaction) clazz).extractFieldValuesToWrite();
                } else if (clazzName.contains("TxListTransaction")) {
                    dataToWrite = ((TxListTransaction) clazz).extractFieldValuesToWrite();
                } else if (clazzName.contains("TxTokenTransaction")) {
                    dataToWrite = ((TxTokenTransaction) clazz).extractFieldValuesToWrite();
                } else if (clazzName.contains("TransactionEntry")) {
                    dataToWrite = ((TransactionEntry) clazz).extractFieldValuesToWrite();
                }

                writer.write(dataToWrite);
                writer.newLine();
            }
        }
    }

    /* TODO FIX -- HOW CAN YOU GUARANTEE THE ORDER :: DATA IS NOT IN ALIGNMENT WITH HEADER FIELDS */
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
//        headerRowFields = headerRowFields.substring(0, headerRowFields.length()-1);
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