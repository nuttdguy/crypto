package org.crypto;

import org.crypto.bsc.BscTransaction;
import org.crypto.quote.QuoteTransaction;
import org.crypto.bsc.account.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.crypto.util.DataTypeUtil.*;

public class CSVReader<T extends Transaction> {

    @SuppressWarnings("unchecked")
    public List<T> readFromCSV(String fileName, Class<?> clazz) throws IOException {
        // extract the class name in order to generify
        String className = clazz.getName().substring(clazz.getName().lastIndexOf(".")+1);
        List<T> transactionList = new ArrayList<>();

        // read from the current user directory; try closes reader by default
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {

            String line;
            boolean skipFirstLine = true;
            while ((line = reader.readLine()) != null) {

                // skip the first line, i.e. header row
                if (skipFirstLine) { skipFirstLine = false; continue; }

                // split the string field values
                String[] fields = line.replace("\"", "").split(",");

                // transform field values into a class instance; T should be known at runtime
                T tx;
                switch (className) {
                    case "BscTransaction" -> tx = (T) toBscTransaction(fields);
                    case "QuoteTransaction" -> tx = (T) toQuoteTransaction(fields);
                    default -> throw new RuntimeException("Unable to convert T");
                }

                // add the record into the list
                transactionList.add(tx);
            }
        }
        return transactionList;
    }

    public List<String> readFromCSV(List<String> files) throws IOException {
        // store file data into a single string
        List<String> fileContent = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (String file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                // append contents of file by row
                String line;
                boolean addHeaderRow = true;
                while ((line = reader.readLine()) != null) {

                    if (addHeaderRow) {
                        // add the header row as a list element
                        fileContent.add(line);
                        addHeaderRow = false;
                        continue;
                    }
                    // append every line to the previous line
                    sb.append(line).append(":ENDLINE");
                }
                // add the string-builder content string as a list element
                fileContent.add(sb.toString());

                // reset string-builder for next file
                sb = new StringBuilder();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return fileContent;

    }

    public static String extractHeaderRowFromCSV(String fileName) {
        // read from the current user directory; try closes reader by default
        String headerRow = "";
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {

            // extract the first row
            headerRow = reader.readLine();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // return the header row
        return headerRow;
    }

    private QuoteTransaction toQuoteTransaction(String[] f) {
        return new QuoteTransaction.TransactionBuilder()
                .withId(toInteger(f[0]))
                .withName(f[1])
                .withSymbol(f[2])
                .withSlug(f[3])
                .withActive(Boolean.parseBoolean(f[4]))
                .withFiat(Boolean.parseBoolean(f[5]))
                .withCirculatingSupply(toInteger(f[6]))
                .withMaxSupply(toDouble(f[7]))
                .withDateAdded(toDateTime(f[8]))
                .withNumMarketPairs(toInteger(f[9]))
                .withRank(toInteger(f[10]))
                .withLastUpdated(toDateTime(f[11]))
                .withTags(toArray(f[12]))
                .withPrice(toDouble(f[13]))
                .withVolume24(toDouble(f[14]))
                .withVolumeChange24(toDouble(f[15]))
                .withPercentChangeHr(toFloat(f[16]))
                .withPercentChange24(toFloat(f[17]))
                .withPercentChangeWk(toFloat(f[18]))
                .withPercentChange30Day(toFloat(f[19]))
                .withMarketCap(toDouble(f[20]))
                .withMarketCapDominance(toInteger(f[21]))
                .withFullyDilutedMarketCap(toDouble(f[22]))
                .build();
    }

    private BscTransaction toBscTransaction(String[] f) {
        return new BscTransaction.TransactionBuilder()
                .withTxHash(f[0])
                .withUnixTimestamp(Long.parseLong(f[1]))
                .withDateTime(toDateTime(f[2]))
                .withFrom(f[3])
                .withTo(f[4])
                .withTokenValue(toDouble(f[5]))
                .withUsdValueDayOfTx(toDouble(f[6]))
                .withContractAddress(f[7])
                .withTokenName(f[8])
                .withTokenSymbol(f[9])
                .build();
    }

}

