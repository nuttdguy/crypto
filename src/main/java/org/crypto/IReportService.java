package org.crypto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.crypto.bsc.account.TxActionType.*;
import static org.crypto.bsc.account.TxActionType.TOKEN_TX;
import static org.crypto.util.JsonUtil.mapObjectsToString;
import static org.crypto.util.JsonUtil.toJsonObject;

public interface IReportService {

    /* create a mapped entry from a header and file contents */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent);

    /* creates a Transaction class instance matching the Api Action= type  */
    public Transaction createTxTransaction(JSONObject jsonObject, String actionType);

    /* creates a Transaction class instance from a mapped entry  */
    public Transaction createTxTransaction(Map<String, String> mapEntry, String actionType);

    /* from json array resource, create a Transaction for every element and return the List */
    public List<Transaction> createTransactionListFrom(JSONArray resourceArray, String actionType);

    /* fetch and get the data from the resource endpoint with params */
    public InputStream fetchApiResource(String resourceUrl, String resourceParams, String APIKEY) throws IOException;

    /* from resource stream, extract the json array using the resource object key */
    public JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceObjectKey) throws JSONException, IOException;

    /* read from file and return a list of Transactions */
    public List<String> readTransactionsFrom(String file) throws IOException;

    /* from a TransactionList, write entries to a file */
    public int writeTransactionsToCsv(List<Transaction> transactions, String fileName, boolean append);

}
