package org.crypto;

import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        // fetch latest quotes
        List<Quote> quoteList = CmcApi.fetchLatestQuotes(new QuoteConfig());

        // write quote results to a file
        CSVWriter<Quote> quoteCSVWriter = new CSVWriter<>();
        try {
            quoteCSVWriter.writeToCSV("crypto_quotes.csv", quoteList);

        } catch(Exception ex) {
            ex.printStackTrace();
        }


        //  READING CSV FILE

            // extract transaction data from bsc explorer csv file
//            CSVReader<BscTransaction> bscTransactionCSVReader = new CSVReader<>();
//            List<BscTransaction> bscTransactionList = bscTransactionCSVReader.readFromCSV("bsc-token-transfers.csv", BscTransaction.class);
//            bscTransactionList.forEach(out::println);

            // read from csv files
//            CSVReader<QuoteTransaction> quoteTransactionCSVReader = new CSVReader<>();
//            List<QuoteTransaction> quoteTransactionList = quoteTransactionCSVReader.readFromCSV("crypto_quotes.csv", QuoteTransaction.class);
//            quoteTransactionList.forEach(out::println);

    }

}