package org.crypto;

import org.crypto.bsc.BscApi;
import org.crypto.bsc.account.BscAccount;
import org.crypto.bsc.account.BscAccountConfig;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        // fetch quotes
//        fetchQuote();

        // fetch bsc account
        fetchBscAccount();



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

    public static void fetchBscAccount() {
        // fetch latest quotes
        BscApi bscApi = new BscApi();
        BscAccountConfig config = new BscAccountConfig
                .AccountConfigBuilder()
                .build("txlist", "0x1c93ba02fcf68fd9bee9e1a15a21495beaf36ad5");

        List<BscAccount> accountEntries = bscApi.fetchBscAccount(config);

        // write BscAccount results to a file
        CSVWriter<BscAccount> quoteCSVWriter = new CSVWriter<>();
        try {
            quoteCSVWriter.writeToCSV("bsc_account.csv", accountEntries);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void fetchQuote() {
        // fetch latest quotes
        CmcApi cmcApi = new CmcApi();
        QuoteConfig config = new QuoteConfig();
        List<Quote> quoteList = cmcApi.fetchLatestQuotes(config);

        // write quote results to a file
        CSVWriter<Quote> quoteCSVWriter = new CSVWriter<>();
        try {
            quoteCSVWriter.writeToCSV("crypto_quotes.csv", quoteList);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}