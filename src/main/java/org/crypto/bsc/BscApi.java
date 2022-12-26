package org.crypto.bsc;

import org.crypto.bsc.account.BscAccount;
import org.crypto.bsc.account.BscAccountConfig;
import org.crypto.bsc.account.BscAccountService;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/* class to execute api requests to  retrieve data from https://api.bscscan.com/api/
* other actions found @ https://docs.bscscan.com/api-endpoints/accounts */
public class BscApi {

    private final BscAccountService bscAccountService = new BscAccountService();

    /* fetch the resource */
    public InputStream fetchBscAccount(BscAccountConfig bscAccountConfig) throws IOException {
        // retrieve response as input stream from api
        return bscAccountService.fetchApiResource(bscAccountConfig.getResourceUrl(), bscAccountConfig.getParamString(), bscAccountConfig.getApikey());
    }

    /* from resource stream, extract the data into a list of bsc account instances */
    public List<BscAccount> createListFrom(InputStream resourceStream, String resourceKey, String actionType) throws JSONException, IOException {
        return bscAccountService.createListFrom(resourceStream, resourceKey, actionType);
    }

}
