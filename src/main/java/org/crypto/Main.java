package org.crypto;

import org.crypto.bsc.BscLabel;
import org.crypto.bsc.BscTransaction;
import org.crypto.quote.CmcQuoteApi;
import org.crypto.quote.QuoteDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<QuoteDetail> quoteDetails = new ArrayList<>();
        List<BscTransaction> bscTransactions = new ArrayList<>();
        String[] labels = Stream.of(BscLabel.values()).map(e -> e.label).toArray(String[]::new);

        try {
//            quoteDetails = CmcQuoteApi.getQuotes(1000, 1);
//            CSVWriter<QuoteDetail> quoteDetailCSVWriter = new CSVWriter<>();
//            quoteDetailCSVWriter.writeToCSV("crypto_quotes.csv", quoteDetails);

            // extract transaction data from bsc explorer csv file
            CSVReader<BscTransaction> csvReader = new CSVReader<>();
            List<BscTransaction> transactionList = csvReader.readFromCSV("bsc-token-transfers.csv", labels);
            transactionList.forEach(System.out::println);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}