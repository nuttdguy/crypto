package org.crypto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public static void writeQuoteToCSV(String fileName, List<Quote> quotes) throws IOException {

        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName))) {
            writer.write("id,name,symbol,slug,cmc_rank,price,volume_24h,market_cap,market_cap_dominance,circulating_supply,total_supply,last_updated");
            writer.newLine();
            for (Quote quote : quotes) {
                writer.write(quote.id + "," +
                        quote.name + "," +
                        quote.symbol + "," +
                        quote.slug + "," +
                        quote.rank + "," +
                        quote.price + "," +
                        quote.volume + "," +
                        quote.marketCap + "," +
                        quote.marketCapDominance + "," +
                        quote.circulatingSupply + "," +
                        quote.totalSupply + "," +
                        quote.lastUpdated);
                writer.newLine();
            }

        }

    }

}