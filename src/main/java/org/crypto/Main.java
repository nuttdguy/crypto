package org.crypto;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Quote> quotes = new ArrayList<>();

        try {
            quotes = CoinMarketCapAPI.getLatestQuotes(5000);
            CSVWriter.writeQuoteToCSV("crypto_quotes.csv", quotes);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}