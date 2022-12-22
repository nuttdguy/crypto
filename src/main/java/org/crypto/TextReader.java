package org.crypto;

import org.crypto.ohlcv.Ohlcv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    public static List<Ohlcv> readFromTextFile(String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        List<Ohlcv> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Timestamp: ")) {
                    long timestamp = Long.parseLong(line.substring(11));
                    double open = Double.parseDouble(reader.readLine().substring(6));
                    double high = Double.parseDouble(reader.readLine().substring(6));
                    double low = Double.parseDouble(reader.readLine().substring(5));
                    double close = Double.parseDouble(reader.readLine().substring(7));
                    double volume = Double.parseDouble(reader.readLine().substring(8));
//                    Ohlcv record = new Ohlcv(timestamp, open, high, low, close, volume);
//                    result.add(record);
                }
            }
        }
        return result;
    }
}
