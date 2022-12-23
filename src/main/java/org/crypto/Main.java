package org.crypto;

import org.crypto.bsc.BscLabel;
import org.crypto.quote.CmcQuoteApi;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        List<Quote> quoteList = new ArrayList<>();

//        List<BscTransaction> bscTransactions = new ArrayList<>();
//        String[] labels = Stream.of(BscLabel.values()).map(e -> e.label).toArray(String[]::new);

        try {
            quoteList = CmcQuoteApi.getQuotes(1000, 1);
            CSVWriter<Quote> quoteCSVWriter = new CSVWriter<>();
            quoteCSVWriter.writeToCSV("crypto_quotes.csv", quoteList);

            // extract transaction data from bsc explorer csv file
//            CSVReader<BscTransaction> bscTransactionCSVReader = new CSVReader<>();
//            List<BscTransaction> bscTransactionList = bscTransactionCSVReader.readFromCSV("bsc-token-transfers.csv", BscTransaction.class);
//            bscTransactionList.forEach(out::println);

            CSVReader<QuoteTransaction> quoteTransactionCSVReader = new CSVReader<>();
            List<QuoteTransaction> quoteTransactionList = quoteTransactionCSVReader.readFromCSV("crypto_quotes.csv", QuoteTransaction.class);
            quoteTransactionList.forEach(out::println);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}