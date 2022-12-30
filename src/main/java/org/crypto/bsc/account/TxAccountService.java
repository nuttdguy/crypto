package org.crypto.bsc.account;

import org.crypto.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.crypto.bsc.account.TxActionType.*;
import static org.crypto.bsc.account.TxLabel.*;
import static org.crypto.util.DataTypeUtil.*;
import static org.crypto.util.JsonUtil.*;

/* service class to fetch /?module=account resource from bsc explorer api */
public class TxAccountService {

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

    /* from json array resource, create a Transaction for every element and return the List */
    public List<Transaction> createTransactionListFrom(JSONArray resourceArray, String actionType) {
        List<Transaction> accountEntries = new ArrayList<>();

        // create a Transaction for every element in the array
        for (int i = 0; i < resourceArray.length(); i++) {
            accountEntries.add(createTxTransaction(resourceArray.getJSONObject(i), actionType));
        }
        return accountEntries;

    }



    //=== PROTECTED ==//

    /* creates a Transaction class instance matching the Api Action= type  */
    protected Transaction createTxTransaction(JSONObject jsonObject, String actionType) {

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

        return null;
    }

    /* create an instance of TxListTransaction from a map entry */
    protected TxListTransaction toTxTokenFrom(Map<String, String> accountEntry) {
        return new TxListTransaction.Builder()
                .withBlockNumber(toInteger(accountEntry.get(BLOCK_NUMBER.label)))
                .withTimeStamp(toDateTime(accountEntry.get(TIMESTAMP.label)))
                .withHash(accountEntry.get(HASH.label))
                .withFrom(accountEntry.get(FROM.label))
                .withTo(accountEntry.get(TO.label))
                .withValue(toDouble(accountEntry.get(VALUE.label)))
                .withContractAddress(accountEntry.get(CONTRACT_ADDRESS.label))
                .withInput(accountEntry.get(INPUT.label))
                .withGas(toDouble(accountEntry.get(GAS.label)))
                .withGasUsed(toDouble(accountEntry.get(GAS_USED.label)))
                .withError(toBoolean(accountEntry.get(CONTRACT_ADDRESS.label)))
                .withConfirmations(toLong(accountEntry.get(CONFIRMATIONS.label)))
                .build();
    }

    /* create an instance of TxInternalTransaction from a map entry */
    protected TxInternalTransaction toTxInternalFrom(Map<String, String> accountEntry) {
        return new TxInternalTransaction.Builder()
                .withBlockNumber(toInteger(accountEntry.get(BLOCK_NUMBER.label)))
                .withTimeStamp(toDateTime(accountEntry.get(TIMESTAMP.label)))
                .withHash(accountEntry.get(HASH.label))
                .withFrom(accountEntry.get(FROM.label))
                .withTo(accountEntry.get(TO.label))
                .withValue(toDouble(accountEntry.get(VALUE.label)))
                .withContractAddress(accountEntry.get(CONTRACT_ADDRESS.label))
                .withInput(accountEntry.get(INPUT.label))
                .withGas(toInteger(accountEntry.get(GAS.label)))
                .withGasUsed(toInteger(accountEntry.get(GAS_USED.label)))
                .withError(toBoolean(accountEntry.get(IS_ERROR.label)))
                .build();
    }

    /* create an instance of TxTokenTransaction from a map entry */
    protected TxTokenTransaction toTxListFrom(Map<String, String> accountEntry) {
        return new TxTokenTransaction.Builder()
                .withFunctionName(accountEntry.get(FUNCTION_NAME.label).replace(",", "::"))
                .withBlockNumber(toInteger(accountEntry.get(BLOCK_NUMBER.label)))
                .withHash(accountEntry.get(HASH.label))
                .withFrom(accountEntry.get(FROM.label))
                .withTo(accountEntry.get(TO.label))
                .withValue(toLong(accountEntry.get(VALUE.label)))
                .withContractAddress(accountEntry.get(CONTRACT_ADDRESS.label))
                .withInput(accountEntry.get(INPUT.label))
                .withGas(toInteger(accountEntry.get(GAS.label)))
                .withGasUsed(toInteger(accountEntry.get(GAS_USED.label)))
                .withNonce(toInteger(accountEntry.get(NONCE.label)))
                .withBlockHash(accountEntry.get(BLOCK_HASH.label))
                .withTokenName(accountEntry.get(TOKEN_NAME.label))
                .withTokenSymbol(accountEntry.get(TOKEN_SYMBOL.label))
                .withTokenDecimal(toInteger(accountEntry.get(TOKEN_DECIMAL.label)))
                .withTransactionIndex(toInteger(accountEntry.get(TRANSACTION_INDEX.label)))
                .withGasPrice(toDouble(accountEntry.get(GAS_PRICE.label)))
                .withCumulativeGasUsed(toInteger(accountEntry.get(CONTRACT_ADDRESS.label)))
                .withConfirmations(toInteger(accountEntry.get(CONFIRMATIONS.label)))
                .build();
    }

}
