package org.crypto;

import org.crypto.bsc.BscTransaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.crypto.Util.isDouble;

public class CSVReader<T extends Transaction> {

    public List<T> readFromCSV(String fileName, String[] labels) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + File.separator + fileName;

        List<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {

            String line;
            boolean skipFirstLine = true;
            while ((line = reader.readLine()) != null) {
                // skip the first line, i.e. header row
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }

                // add transaction record
                String[] fields = line.replace("\"", "").split(",");
                T tx = toBscTransaction(fields);
                result.add(tx);
            }
        }
        return result;
    }

    private T toBscTransaction(String[] fields) {
        String txHash = fields[0];
        long unixTimestamp = Long.parseLong(fields[1]);
        LocalDateTime dateTime = LocalDateTime.parse(fields[2].replace(" ", "T"));
        String from = fields[3];
        String to = fields[4];
        double tokenValue = Double.parseDouble(fields[5]);

        double usdValueDayOfTx = 0.00;
        if (isDouble(fields[6])) {
            usdValueDayOfTx = Double.parseDouble(fields[6]);
        }

        String contractAddress = fields[7];
        String tokenName = fields[8];
        String tokenSymbol = fields[9];

        BscTransaction transaction = new BscTransaction(
                txHash,
                unixTimestamp,
                dateTime,
                from,
                to,
                tokenValue,
                usdValueDayOfTx,
                contractAddress,
                tokenName,
                tokenSymbol);

        return (T) transaction;

    }


}

