package org.crypto.bsc.account;

import org.crypto.CSVReader;
import org.crypto.CSVWriter;
import org.crypto.HttpException;
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
import static org.crypto.bsc.account.TxLabel.*;
import static org.crypto.util.DataTypeUtil.*;
import static org.crypto.util.JsonUtil.*;

/* service class to fetch /?module=account resource from bsc explorer api */
public class TxAccountService {



    /* create a mapped entry from a header and file contents */
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent) {
        List<Map<String, String>> entries = new ArrayList<>();

        // iterate through each row entry
        for (String row : fileContent) {
            Map<String, String> entry = new HashMap<>();
            String[] content = row.split(",");  // split the current row into single elements

            int i = 0;
            for (String header : headerRow) {
                // map the header and field values for the current row
                entry.putIfAbsent(header, content[i]);
                i++;
            }
            // add the map entry to the list
            entries.add(entry);
        }
        return entries;
    }


    /* creates a Transaction class instance matching the Api Action= type  */
    public Transaction createTxTransaction(JSONObject jsonObject, String actionType) {

        // extract all key and value pairs
        Map<String, String> keyValuePairs = mapObjectsToString(jsonObject);

        // create a Transaction matching the action type
        // map key & value pairs into list of class instances
        if (actionType.equals(TX_LIST.label)) {
            return toTxListFrom(keyValuePairs);

        } else if (actionType.equals(TX_LIST_INTERNAL.label)) {
            return toTxInternalFrom(keyValuePairs);

        } else if (actionType.equals(TOKEN_TX.label)) {
            return toTxTokenFrom(keyValuePairs);
        }

        throw new RuntimeException(format("%s action type is not supported", actionType));
    }


    /* creates a Transaction class instance from a mapped entry  */
    public Transaction createTxTransaction(Map<String, String> mapEntry, String actionType) {

        // create a Transaction matching the action type
        // map key & value pairs into list of class instances
        if (actionType.equals(TX_LIST.label)) {
            return toTxListFrom(mapEntry);

        } else if (actionType.equals(TX_LIST_INTERNAL.label)) {
            return toTxInternalFrom(mapEntry);

        } else if (actionType.equals(TOKEN_TX.label)) {
            return toTxTokenFrom(mapEntry);
        }

        throw new RuntimeException(format("%s action type is not supported", actionType));
    }


    /* from json array resource, create a Transaction for every element and return the List */
    public List<Transaction> createTransactionListFrom(JSONArray resourceArray, String actionType) {
        List<Transaction> accountEntries = new ArrayList<>();

        // create a Transaction for every element in the array
        for (int i = 0; i < resourceArray.length(); i++) {
            accountEntries.add(createTxTransaction(resourceArray.getJSONObject(i), actionType));
        }
        return accountEntries;
    }


    /* fetch and get the data from the resource endpoint with params */
    public InputStream fetchApiResource(String resourceUrl, String resourceParams, String APIKEY) throws IOException {
        // create url and open connection
        URL url = new URL(resourceUrl + resourceParams + APIKEY) ;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // set http header params
        httpURLConnection.setRequestMethod("GET");

        // validate response for valid status code
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new HttpException("HTTP Response: " + responseCode);
        }

        // retrieve the response as an input stream
        return httpURLConnection.getInputStream();
    }


    /* from resource stream, extract the json array using the resource object key
     * throws JSONException if the resourceKey does not exist
     * throws IOException when resourceStream cannot be converted to a JsonObject
     * */
    public JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceObjectKey) throws JSONException, IOException {
        // extract the data array from the json object
        JSONObject resource = toJsonObject(resourceStream);
        return resource.getJSONArray(resourceObjectKey);
    }


    /* read from file and return a list of Transactions */
    public List<String> readTransactionsFrom(String file) throws IOException {
        // get file contents as a list
        CSVReader csvReader = new CSVReader();
        return csvReader.readFrom(file);
    }


    /* from a TransactionList, write entries to a file */
    public int writeTransactionsToCsv(List<Transaction> transactions, String fileName, boolean append) {
        // write BscAccount results to a file
        CSVWriter<Transaction> csvWriter = new CSVWriter<>();
        String fileExt = "_transactions.csv";
        try {
            csvWriter.writeToCSV(fileName + fileExt, transactions, append);
            return 1;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }


    //=== PROTECTED - FOR MAPPING VALUES TO CLASS INSTANCES ==//

    /* create an instance of TxListTransaction from a map entry */
    protected TxTokenTransaction toTxTokenFrom(Map<String, String> entry) {
        return new TxTokenTransaction.Builder()
                .withBlockHash(entry.get(BLOCK_HASH.label))
                .withBlockNumber(toInteger(entry.get(BLOCK_NUMBER.label)))
                .withConfirmations(toInteger(entry.get(CONFIRMATIONS.label)))
                .withContractAddress(entry.get(CONTRACT_ADDRESS.label))
                .withCumulativeGasUsed(toInteger(entry.get(CONTRACT_ADDRESS.label)))
                .withFrom(entry.get(FROM.label))
                .withGas(toInteger(entry.get(GAS.label)))
                .withGasPrice(toDouble(entry.get(GAS_PRICE.label)))
                .withGasUsed(toInteger(entry.get(GAS_USED.label)))
                .withHash(entry.get(HASH.label))
                .withInput(entry.get(INPUT.label))
                .withNonce(toInteger(entry.get(NONCE.label)))
                .withTo(entry.get(TO.label))
                .withTokenDecimal(toInteger(entry.get(TOKEN_DECIMAL.label)))
                .withTokenName(entry.get(TOKEN_NAME.label))
                .withTokenSymbol(entry.get(TOKEN_SYMBOL.label))
                .withTransactionIndex(toInteger(entry.get(TRANSACTION_INDEX.label)))
                .withValue(toLong(entry.get(VALUE.label)))
                .build();
    }

    /* create an instance of TxInternalTransaction from a map entry */
    protected TxInternalTransaction toTxInternalFrom(Map<String, String> entry) {
        return new TxInternalTransaction.Builder()
                .withBlockNumber(toInteger(entry.get(BLOCK_NUMBER.label)))
                .withContractAddress(entry.get(CONTRACT_ADDRESS.label))
                .withErrCode(entry.get(ERR_CODE.label))
                .withFrom(entry.get(FROM.label))
                .withGas(toInteger(entry.get(GAS.label)))
                .withGasUsed(toInteger(entry.get(GAS_USED.label)))
                .withHash(entry.get(HASH.label))
                .withInput(entry.get(INPUT.label))
                .withError(toBoolean(entry.get(IS_ERROR.label)))
                .withTimeStamp(toDateTime(entry.get(TIMESTAMP.label)))
                .withTo(entry.get(TO.label))
                .withTraceId(entry.get(TRACE_ID.label))
                .withType(entry.get(TYPE.label))
                .withValue(toDouble(entry.get(VALUE.label)))
                .build();
    }

    /* create an instance of TxTokenTransaction from a map entry */
    protected TxListTransaction toTxListFrom(Map<String, String> entry) {
        return new TxListTransaction.Builder()
                .withBlockHash(entry.get(BLOCK_HASH.label))
                .withBlockNumber(toInteger(entry.get(BLOCK_NUMBER.label)))
                .withConfirmations(toLong(entry.get(CONFIRMATIONS.label)))
                .withContractAddress(entry.get(CONTRACT_ADDRESS.label))
                .withCumulativeGasUsed(toInteger(entry.get(CONTRACT_ADDRESS.label)))
                .withFrom(entry.get(FROM.label))
                .withFunctionName(entry.get(FUNCTION_NAME.label).replace(",", "::"))
                .withGas(toInteger(entry.get(GAS.label)))
                .withGasPrice(toDouble(entry.get(GAS_PRICE.label)))
                .withGasUsed(toInteger(entry.get(GAS_USED.label)))
                .withHash(entry.get(HASH.label))
                .withInput(entry.get(INPUT.label))
                .withError(toBoolean(entry.get(IS_ERROR.label)))
                .withNonce(toInteger(entry.get(NONCE.label)))
                .withTimeStamp(toDateTime(entry.get(TIMESTAMP.label)))
                .withTo(entry.get(TO.label))
                .withTransactionIndex(toInteger(entry.get(TRANSACTION_INDEX.label)))
                .withTxreceipt_status(toInteger(entry.get(TX_RECEIPT_STATUS.label)))
                .withValue(toLong(entry.get(VALUE.label)))
                .build();
    }

}
