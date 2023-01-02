package org.crypto.bsc;


import org.crypto.Transaction;
import org.crypto.bsc.account.TxActionType;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxAccountService;
import org.json.JSONArray;
import org.json.JSONException;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.lang.String.format;


/* class to execute api requests to  retrieve data from https://api.bscscan.com/api/
* other actions found @ https://docs.bscscan.com/api-endpoints/accounts */
public class BscApi {
    // mot implemented modules ["contracts", "transactions", "blocks", "tokens"]
    // not implemented actions ["tokennfttx", "getminedblocks"]


    //== DECLARED FIELDS ==//
    private final String jsonResourceKey = "result";
    private final TxAccountService txAccountService = new TxAccountService();


    //=== BEGIN METHODS ==//

    /* public method to retrieve transactions*/
    public List<Transaction> fetchTxTransactions(TxConfig txConfig) throws IOException {

        InputStream apiStream = fetchBscAccount(txConfig);
        // create a list from the returned resource stream
        JSONArray jsonArrayResource = extractJsonArrayFrom(apiStream, jsonResourceKey);
        return createTransactionListFrom(jsonArrayResource,  txConfig.getAction().label);

    }


    //== PUBLIC


    /* create a mapped entry from a header and file contents */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent) {
        return txAccountService.createMappedEntriesFrom(headerRow, fileContent);
    }

    /* from the file contents, returns a list of transactions */
    public List<Transaction> extractTransactionsFrom(String file, Class<? extends Transaction> clazz) throws IOException {

        // read and return the file contents
        List<String> fileContents = txAccountService.readTransactionsFrom(file);

        String actionType = "";
        String[] clazzNameParts = clazz.getSimpleName().split("\\.");

        // determine the transaction type to create
        switch (clazzNameParts[clazzNameParts.length-1]) {
            case "TxTokenTransaction" -> actionType = TxActionType.TOKEN_TX.label;
            case "TxListTransaction" -> actionType = TxActionType.TX_LIST.label;
            case "TxInternalTransaction" -> actionType = TxActionType.TX_LIST_INTERNAL.label;
            default -> throw new RuntimeException(format("%s is not supported", clazzNameParts[clazzNameParts.length-1]));
        }

        // transform file contents into mapped entries
        List<Map<String, String>> entries = txAccountService.createMappedEntriesFrom(fileContents.get(0).split(","), fileContents.get(1).split("/n/r"));

        // extract the file contents from the second element
        List<Transaction> transactionList = new ArrayList<>();
        for (Map<String, String> entry : entries) {
            Transaction transaction = txAccountService.createTxTransaction(entry, actionType);
            transactionList.add(transaction);
        }

        // return the list of transactions
        return transactionList;
    }

    /* read from file and return contents as String elements */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        return txAccountService.readTransactionsFrom(file);
    }

    /* from a TransactionList, write entries to a file with _transactions.csv appended to the fileName */
    public int writeTransactionsToCsv(List<Transaction> transactionList, String fileName, boolean append, boolean includeHeader) {
        return txAccountService.writeTransactionsToCsv(transactionList, fileName, append, includeHeader);
    }


    //== PROTECTED

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
    protected List<Transaction> createTransactionListFrom(JSONArray jsonArrayResource, String actionType) throws JSONException {
        return txAccountService.createTransactionListFrom(jsonArrayResource, actionType);
    }

}
