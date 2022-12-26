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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.crypto.bsc.account.BscAccountLabel.*;
import static org.crypto.util.DataTypeUtil.*;
import static org.crypto.util.JsonUtil.*;

/* service class to fetch /?module=account resource from bsc explorer api */
public class BscAccountService {

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

    /* from resource stream, extract the data into a list of bsc account instances
     * throws JSONException if the resourceKey does not exist
     * throws IOException when resourceStream cannot be converted to a JsonObject
     * */
    public List<BscAccount> createListFrom(InputStream resourceStream, String resourceKey) throws JSONException, IOException {
        List<BscAccount> accountEntries = new ArrayList<>();

        // transform response into desired format, i.e. string, json, etc
        JSONObject resource = toJsonObject(resourceStream);

        // extract the data array from the json object
        JSONArray resourceArray = resource.getJSONArray(resourceKey);

        for (int i = 0; i < resourceArray.length(); i++) {

            // create and then add bsc account instance
            accountEntries.add(createAccountEntry(resourceArray.getJSONObject(i)));
        }
        return accountEntries;

    }

    /* creates an instance of bscAccount  */
    public BscAccount createAccountEntry(JSONObject accountEntry) {
        // if results is valid, extract all the keys and value types
        Map<String, Object> keyPairs =
                extractKeyPairs(accountEntry, new HashMap<>(), true);

        // extract all values into String values
        Map<String, String> keyValuePairs = mapObjectsToString(keyPairs);

        // map key & value pairs into list of class instances
        return toAccountFromMap(keyValuePairs);
    }


    /* create an instance of BscAccount from a map entry */
    protected BscAccount toAccountFromMap(Map<String, String> accountEntry) {
        return new BscAccount.AccountBuilder()
                .withFunctionName(accountEntry.get(FUNCTION_NAME.label).replace(",", "::"))
                .withBlockNumber(accountEntry.get(BLOCK_NUMBER.label))
                .withTimeStamp(toDateTime(accountEntry.get(TIMESTAMP.label)))
                .withHash(accountEntry.get(HASH.label))
                .withNonce(toInteger(accountEntry.get(NONCE.label)))
                .withBlockHash(accountEntry.get(BLOCK_HASH.label))
                .withTransactionIndex(toInteger(accountEntry.get(TRANSACTION_INDEX.label)))
                .withFrom(accountEntry.get(FROM.label))
                .withTo(accountEntry.get(TO.label))
                .withValue(toDouble(accountEntry.get(VALUE.label)))
                .withGas(toDouble(accountEntry.get(GAS.label)))
                .withGasPrice(toDouble(accountEntry.get(GAS_PRICE.label)))
                .withError(toBoolean(accountEntry.get(IS_ERROR.label)))
                .withTxreceipt_status(toInteger(accountEntry.get(TX_RECEIPT_STATUS.label)))
                .withInput(accountEntry.get(INPUT.label))
                .withContractAddress(accountEntry.get(CONTRACT_ADDRESS.label))
                .withCumulativeGasUsed(toDouble(accountEntry.get(CONTRACT_ADDRESS.label)))
                .withGasUsed(toDouble(accountEntry.get(GAS_USED.label)))
                .withConfirmations(toLong(accountEntry.get(CONFIRMATIONS.label)))
                .withMethodId(accountEntry.get(METHOD_ID.label))
                .build();
    }

}
