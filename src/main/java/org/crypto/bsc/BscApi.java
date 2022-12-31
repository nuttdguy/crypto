package org.crypto.bsc;


import org.crypto.bsc.account.Transaction;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxAccountService;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/* class to execute api requests to  retrieve data from https://api.bscscan.com/api/
* other actions found @ https://docs.bscscan.com/api-endpoints/accounts */
public class BscApi {
    // mot implemented modules ["contracts", "transactions", "blocks", "tokens"]
    // not implemented actions ["tokennfttx", "getminedblocks"]

    private final String jsonResourceKey = "result";

    private final TxAccountService txAccountService = new TxAccountService();

    /* fetch the resource */
    protected InputStream fetchBscAccount(TxConfig txConfig) throws IOException {
        // retrieve response as input stream from api
        return txAccountService.fetchApiResource(txConfig.getResourceUrl(), txConfig.getParamString(), txConfig.getApikey());
    }

    /* extract a json array from the input stream resource */
    protected JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceKey) throws IOException {
        return txAccountService.extractJsonArrayFrom(resourceStream, resourceKey);
    }

    /* from a json array, extract the data into a list of transactions  */
    protected List<Transaction> createTransactionListFrom(JSONArray jsonArrayResource, String actionType) throws JSONException, IOException {
        return txAccountService.createTransactionListFrom(jsonArrayResource, actionType);
    }

    /* from a TransactionList, write entries to a file with _transaction.csv appended to the fileName */
    public int writeTransactionsToCsv(List<Transaction> transactionList, String fileName, boolean append) {
        return txAccountService.writeTransactionsToCsv(transactionList, fileName, append);
    }

    /* read from file and return contents as String elements */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        return txAccountService.readTransactionsFrom(file);
    }

    public List<Transaction> extractTransactionsFrom(String file) throws IOException {
        List<String> fileContents = readTransactionsFrom(file);
        List<Transaction> transactions = new ArrayList<>();

        // todo convert string into transactions


        return transactions;

    }

    /* public method to retrieve transactions*/
    public List<Transaction> fetchTxTransactions(TxConfig txConfig) throws IOException {

        InputStream apiStream = fetchBscAccount(txConfig);
        // create a list from the returned resource stream
        JSONArray jsonArrayResource = extractJsonArrayFrom(apiStream, jsonResourceKey);
        return createTransactionListFrom(jsonArrayResource,  txConfig.getAction().label);

    }

}
