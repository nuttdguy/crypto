package org.crypto;

import org.crypto.quote.Quote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter<T> {

    public CSVWriter() {
        // nothing to implement
    }

    /* Write */
    public void writeToCSV(String fileName, List<T> clazzList) throws IOException {

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName))) {

            writer.write(fields);
            writer.newLine();
            for (T clazz : clazzList) {
                String dataToWrite = "";

                if (clazzName.contains("Quote")) {
                    dataToWrite = toStringFrom((Quote) clazz);
                }

                writer.write(dataToWrite);
                writer.newLine();
            }
        }
    }


    private String toStringFrom(Quote quote) {
        String tags = Stream.of(quote.getTags())
                .map(tag -> tag.replace(",", "::"))
                .collect(Collectors.joining(""));

        return quote.getId() + "," +
                quote.getName() + "," +
                quote.getSymbol() + "," +
                quote.getSlug() + "," +
                quote.isActive() + "," +
                quote.isFiat() + "," +
                quote.getCirculatingSupply() + "," +
                quote.getMaxSupply() + "," +
                quote.getDateAdded() + "," +
                quote.getNumMarketPairs() + "," +
                quote.getRank() + "," +
                quote.getLastUpdated() + "," +
                tags + "," +
                quote.getPrice() + "," +
                quote.getVolume24() + "," +
                quote.getVolumeChange24() + "," +
                quote.getPercentChangeHr() + "," +
                quote.getPercentChange24() + "," +
                quote.getPercentChangeWk() + "," +
                quote.getPercentChange30Day() + "," +
                quote.getMarketCap() + "," +
                quote.getMarketCapDominance() + "," +
                quote.getFullyDilutedMarketCap() + "," +
                quote.getLastUpdated();
    }

}