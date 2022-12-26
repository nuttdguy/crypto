package org.crypto.bsc;

import org.crypto.bsc.account.BscAccount;
import org.crypto.bsc.account.BscAccountConfig;
import org.crypto.bsc.account.BscAccountService;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public List<BscAccount> fetchBscAccount(BscAccountConfig bscAccountConfig) {
        List<BscAccount> accountEntries = new ArrayList<>();

        try {
            // retrieve response as input stream from api
            InputStream resourceInputStream =
                    bscAccountService.fetchApiResource(bscAccountConfig.getResourceUrl(), bscAccountConfig.getParamString(), bscAccountConfig.getApikey());

            // transform response into desired format, i.e. string, json, etc
            JSONObject resource = toJsonObject(resourceInputStream);

            // extract the data array from the json object
            JSONArray resourceArray = resource.getJSONArray("result");

            for (int i = 0; i < resourceArray.length(); i++) {

                // if results is valid, extract all the keys and value types
                Map<String, Object> keyPairs =
                        extractKeyPairs(resourceArray.getJSONObject(i), new HashMap<>(), true);

                // extract all values into String values
                Map<String, String> keyValuePairs = mapObjectsToString(keyPairs);

                // map key & value pairs into list of class instances
                accountEntries.add(bscAccountService.toAccountFromMap(keyValuePairs));

            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return accountEntries;

    }

}
