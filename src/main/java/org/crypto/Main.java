package org.crypto;

import org.crypto.quote.CmcQuoteApi;
import org.crypto.quote.QuoteDetail;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<QuoteDetail> quoteDetails = new ArrayList<>();

        try {
            quoteDetails = CmcQuoteApi.getQuotes(1000, 1);
            CSVWriter<QuoteDetail> quoteDetailCSVWriter = new CSVWriter<>();
            quoteDetailCSVWriter.writeToCSV("crypto_quotes.csv", quoteDetails);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}