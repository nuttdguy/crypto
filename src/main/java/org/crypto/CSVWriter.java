package org.crypto;

import org.crypto.quote.Quote;
import org.crypto.quote.QuoteDetail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVWriter<T> {

    public CSVWriter() {
        // nothing to implement
    }

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
            for (T generic : clazzList) {
                String dataToWrite = "";

                if (clazzName.contains("QuoteDetail")) {
                    dataToWrite = toStringFrom((QuoteDetail) generic);
                }

                writer.write(dataToWrite);
                writer.newLine();
            }
        }
    }


    private String toStringFrom(QuoteDetail quoteDetail) {
        return this.toStringFromQuote(quoteDetail) + "," +
                quoteDetail.getPrice() + "," +
                quoteDetail.getVolume24() + "," +
                quoteDetail.getVolumeChange24() + "," +
                quoteDetail.getPercentChangeHr() + "," +
                quoteDetail.getPercentChange24() + "," +
                quoteDetail.getPercentChangeWk() + "," +
                quoteDetail.getPercentChange30Day() + "," +
                quoteDetail.getMarketCap() + "," +
                quoteDetail.getMarketCapDominance() + "," +
                quoteDetail.getFullyDilutedMarketCap() + "," +
                quoteDetail.getLastUpdated();
    }

    private String toStringFromQuote(Quote quote) {
        String tags = Arrays.toString(quote.getTags()).replace(",", "::");

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
                tags;
    }

}