package org.crypto.bsc.account;

import org.crypto.HttpException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static org.crypto.bsc.account.BscAccountLabel.*;
import static org.crypto.util.DataTypeUtil.*;

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

    /* create an instance of BscAccount from a map entry */
    public BscAccount toAccountFromMap(Map<String, String> accountEntry) {
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
