package org.crypto;

import org.crypto.bsc.BscTransaction;
import org.crypto.quote.QuoteTransaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.crypto.Util.*;

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


                // todo add validation for checking field header equals to field values
                // e.g. fields row length  == fields header rows length

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

    private QuoteTransaction toQuoteTransaction(String[] f) {
        // to implement
        return new QuoteTransaction.TransactionBuilder()
                .withId(Integer.parseInt(f[0]))
                .withName(f[1])
                .withSymbol(f[2])
                .withSlug(f[3])
                .withActive(Boolean.parseBoolean(f[4]))
                .withFiat(Boolean.parseBoolean(f[5]))
                .withCirculatingSupply(isInt(f[6]) ? Integer.parseInt(f[6]) : 0)
                .withMaxSupply(isDouble(f[7]) ? Double.parseDouble(f[7]) : 0.00)
                .withDateAdded(LocalDateTime.parse(f[8].replace(" ", "T")))
                .withNumMarketPairs(isInt(f[9]) ? Integer.parseInt(f[9]) : 0)
                .withRank(isInt(f[10]) ? Integer.parseInt(f[10]) : 0)
                .withLastUpdated(LocalDateTime.parse(f[11].replace(" ", "T")))
                .withTags(toArray(f[12]))
                .withPrice(isDouble(f[13]) ? Double.parseDouble(f[13]) : 0.00)
                .withVolume24(isDouble(f[14]) ? Double.parseDouble(f[14]) : 0.00)
                .withVolumeChange24(isDouble(f[15]) ? Double.parseDouble(f[15]) : 0.00)
                .withPercentChangeHr(isFloat(f[16]) ? Float.parseFloat(f[16]) : 0.00f)
                .withPercentChange24(isFloat(f[17]) ? Float.parseFloat(f[17]) : 0.00f)
                .withPercentChangeWk(isFloat(f[18]) ? Float.parseFloat(f[18]) : 0.00f)
                .withPercentChange30Day(isFloat(f[19]) ? Float.parseFloat(f[19]) : 0.00f)
                .withMarketCap(isDouble(f[20]) ? Double.parseDouble(f[20]) : 0.00)
                .withMarketCapDominance(isInt(f[21]) ? Integer.parseInt(f[21]) : 0)
                .withFullyDilutedMarketCap(isDouble(f[22]) ? Double.parseDouble(f[22]) : 0.00)
                .build();
    }

    private BscTransaction toBscTransaction(String[] fields) {
        return new BscTransaction.TransactionBuilder()
                .withTxHash(fields[0])
                .withUnixTimestamp(Long.parseLong(fields[1]))
                .withDateTime(LocalDateTime.parse(fields[2].replace(" ", "T")))
                .withFrom(fields[3])
                .withTo(fields[4])
                .withTokenValue( Double.parseDouble(fields[5]))
                .withUsdValueDayOfTx(isDouble(fields[6]) ? Double.parseDouble(fields[6]) : 0.00)
                .withContractAddress(fields[7])
                .withTokenName(fields[8])
                .withTokenSymbol(fields[9])
                .build();
    }


}

