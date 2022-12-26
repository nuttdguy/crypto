package org.crypto.bsc;

import org.crypto.bsc.account.BscAccount;
import org.crypto.bsc.account.BscAccountConfig;
import org.crypto.bsc.account.BscAccountService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.crypto.util.JsonUtil.*;

/* class to execute api requests to  retrieve data from https://api.bscscan.com/api/
* ?/module/account
* &action=balance
* &address=0x.....
* &apikey=eds3....
* other actions found @ https://docs.bscscan.com/api-endpoints/accounts */
public class BscApi {

    private final BscAccountService bscAccountService = new BscAccountService();

    /* fetch the resource */
    public InputStream fetchBscAccount(BscAccountConfig bscAccountConfig) throws IOException {
        // retrieve response as input stream from api
        return bscAccountService.fetchApiResource(bscAccountConfig.getResourceUrl(), bscAccountConfig.getParamString(), bscAccountConfig.getApikey());
    }

    /* from resource, extract the data into a list of bsc account instances
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
    private BscAccount createAccountEntry(JSONObject accountEntry) {
        // if results is valid, extract all the keys and value types
        Map<String, Object> keyPairs =
                extractKeyPairs(accountEntry, new HashMap<>(), true);

        // extract all values into String values
        Map<String, String> keyValuePairs = mapObjectsToString(keyPairs);

        // map key & value pairs into list of class instances
        return bscAccountService.toAccountFromMap(keyValuePairs);
    }


}
