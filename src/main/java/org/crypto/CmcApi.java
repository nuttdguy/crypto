package org.crypto;

import org.crypto.quote.QuoteService;
import org.crypto.quote.Quote;
import org.crypto.quote.QuoteConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.crypto.util.JsonUtil.*;

/* class to execute api requests to retrieve data from https://coinmarketcap.com */
public class CmcApi {

    private final QuoteService quoteService = new QuoteService();

    /* fetch /quotes resource from coin market map */
    public List<Quote> fetchLatestQuotes(QuoteConfig quoteConfig) {
        List<Quote> quoteList = new ArrayList<>();

        try {
            // retrieve response as input stream from api
            InputStream resourceInputStream =
                    quoteService.fetchApiResource(quoteConfig.getResourceUrl(), quoteConfig.getParamString(), quoteConfig.getAPIKey());

            // transform response into desired format, i.e. string, json, etc
            JSONObject resource = toJsonObject(resourceInputStream);

            // extract the data array from the json object
            JSONArray resourceArray = resource.getJSONArray("data");

            for (int i = 0; i < resourceArray.length(); i++) {

                // extract all the keys and types
                Map<String, Object> keyPairs =
                        extractKeyAndJsonType(resourceArray.getJSONObject(i), new HashMap<>(), true);

                // extract all values into String values
                Map<String, String> keyValuePairs = mapObjectsToString(keyPairs);

                // map key & value pairs into list of class instances
                quoteList.add(quoteService.toQuoteFromMap(keyValuePairs));

            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return quoteList;
    }

}
