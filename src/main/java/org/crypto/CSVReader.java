package org.crypto;

import org.crypto.ohlcv.Ohlcv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Ohlcv> readFromCSV(String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        List<Ohlcv> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
//                String[] fields = line.split(",");
//                long timestamp = Long.parseLong(fields[0]);
//                double open = Double.parseDouble(fields[1]);
//                double high = Double.parseDouble(fields[2]);
//                double low = Double.parseDouble(fields[3]);
//                double close = Double.parseDouble(fields[4]);
//                double volume = Double.parseDouble(fields[5]);
//                records.add(new Ohlcv(timestamp, open, high, low, close, volume));
            }
        }

        return records;
    }
}
