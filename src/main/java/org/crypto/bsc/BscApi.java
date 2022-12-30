package org.crypto.bsc;

import org.crypto.bsc.account.Transaction;
import org.crypto.bsc.account.TxConfig;
import org.crypto.bsc.account.TxAccountService;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/* class to execute api requests to  retrieve data from https://api.bscscan.com/api/
* other actions found @ https://docs.bscscan.com/api-endpoints/accounts */
public class BscApi {

    private final TxAccountService txAccountService = new TxAccountService();

    /* fetch the resource */
    public InputStream fetchBscAccount(TxConfig txConfig) throws IOException {
        // retrieve response as input stream from api
        return txAccountService.fetchApiResource(txConfig.getResourceUrl(), txConfig.getParamString(), txConfig.getApikey());
    }

    /* extract a json array from the input stream resource */
    public JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceKey) throws IOException {
        return txAccountService.extractJsonArrayFrom(resourceStream, resourceKey);
    }

    /* from a json array, extract the data into a list of transactions  */
    public List<Transaction> createTransactionListFrom(JSONArray jsonArrayResource, String actionType) throws JSONException, IOException {
        return txAccountService.createTransactionListFrom(jsonArrayResource, actionType);
    }


}
