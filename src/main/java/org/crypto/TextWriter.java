package org.crypto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextWriter {

    public static void writeToTextFile(String fileName, List<OHLCVRecord> records) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (OHLCVRecord record : records) {
                writer.write("Timestamp: " + record.timestamp + "\n"
                        + "Open: " + record.open + "\n"
                        + "High: " + record.high + "\n"
                        + "Low: " + record.low + "\n"
                        + "Close: " + record.close + "\n"
                        + "Volume: " + record.volume + "\n");
                writer.newLine();
            }
        }
    }
}