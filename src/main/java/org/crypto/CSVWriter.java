package org.crypto;

import org.crypto.model.Quote;
import org.crypto.model.TokenInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class CSVWriter {

    public static void writeToCSV(String fileName, List<OHLCVRecord> records) throws IOException {

        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("timestamp,open,high,low,close,volume");
            writer.newLine();
            for (OHLCVRecord record : records) {
                writer.write(record.timestamp + "," + record.open + "," + record.high + "," + record.low + "," + record.close + "," + record.volume);
                writer.newLine();
            }
        }
    }

    public static <T extends TokenInfo> void writeQuoteToCSV(String fileName, List<T> clazzList) throws IOException, ClassNotFoundException {

        // return when list is empty
        if (clazzList.isEmpty()) { return; }

        // todo add method to format to exact match of api field, i.e. underscore and refactor
        // extract fields and get the class name
        String clazzName = clazzList.get(0).getClass().getName();
        Class<?> currentClazz = Class.forName(clazzName);
        Field[] fieldNames = currentClazz.getDeclaredFields();
        Class<?> superClazz = currentClazz.getSuperclass();
        Field[] superFieldNames = superClazz.getDeclaredFields();

        // extract fields of the class to write to file
        String fields = Stream.of(fieldNames)
                .map(Field::getName)
                .map(field -> field.split("\\.")[0])
                .collect(Collectors.joining(","));

        String superFields = Stream.of(superFieldNames)
                .map(Field::getName)
                .map(field -> field.split("\\.")[0])
                .collect(Collectors.joining(","));

        fields = superFields + "," + fields;

        // get the current directory, append to the filename
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName))) {

            writer.write(fields);
            writer.newLine();
            for (T generic : clazzList) {
                String dataToWrite = "";

                if (clazzName.contains("Quote")) {
                    dataToWrite = toStringFrom((Quote) generic);
                }

                writer.write(dataToWrite);
                writer.newLine();
            }
        }
    }

    private static String toStringFrom(Quote quote) {
        return toStringFromToken(quote) + "," +
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

    private static String toStringFromToken(TokenInfo tokenInfo) {
        String tags = Arrays.toString(tokenInfo.getTags()).replace(",", "::");
//        out.println(tags);

        return tokenInfo.getId() + "," +
                tokenInfo.getName() + "," +
                tokenInfo.getSymbol() + "," +
                tokenInfo.getSlug() + "," +
                tokenInfo.isActive() + "," +
                tokenInfo.isFiat() + "," +
                tokenInfo.getCirculatingSupply() + "," +
                tokenInfo.getMaxSupply() + "," +
                tokenInfo.getDateAdded() + "," +
                tokenInfo.getNumMarketPairs() + "," +
                tokenInfo.getRank() + "," +
                tokenInfo.getLastUpdated() + "," +
                tags;
    }

}