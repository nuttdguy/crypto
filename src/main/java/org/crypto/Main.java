package org.crypto;

import org.crypto.model.Quote;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Quote> quotes = new ArrayList<>();

        try {
            quotes = CoinMarketCapAPI.getQuotes(1000, 1);
            CSVWriter.writeQuoteToCSV("crypto_quotes.csv", quotes);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}