package org.crypto.quote;

import org.crypto.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static org.crypto.Util.*;
import static org.crypto.quote.QuoteLabel.*;
import static org.crypto.quote.QuoteLabel.LAST_UPDATED;

/* Coin Market Cap API options for v1 /listing & v2 /quotes endpoint */
public class CmcQuoteService {

    /* fetch and get the data from the resource endpoint with params
    *  endpoint for quote resource; v2/cryptocurrency/quotes/latest, v1/cryptocurrency/listings/latest
    *  expected json keys for response, v2 requires one or more comma separated ids of the tokens
    * */
    public InputStream fetchApiResource(String resourceUrl, String resourceParams, String APIKEY) throws IOException {
        // create url and open connection
        URL url = new URL(resourceUrl + resourceParams);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // set http header params
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("X-CMC_PRO_API_KEY", APIKEY);

        // validate response for valid status code
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new HttpException("HTTP Response: " + responseCode);
        }

        // retrieve the response as an input stream
        return httpURLConnection.getInputStream();
    }

    public JSONObject toJsonObject(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return new JSONObject(sb.toString());
    }

    /* transform json native object values types, e.g. Integer, BigDecimal, Array -> String, into String format */
    public Map<String, String> mapObjectsToString(Map<String, Object> resourceKeys) {
        Map<String, String> mapEntries = new HashMap<>();
        for (var entry : resourceKeys.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject)) {
                mapEntries.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return mapEntries;
    }

    /* extract json array by object key */
    public JSONArray extractJsonArray(JSONObject resource, String key) {
        return resource.getJSONArray(key);
    }

    /* extract json object by object key */
    public JSONObject extractJsonObject(JSONObject resource, String key) {
        return resource.getJSONObject(key);
    }

    /* extract all objects and array key types; optional -> include other native type, e.g. Integer, BigDecimal
    *  resource: the json object
    *  keys: an empty map for adding String key and Object
    *  includeNonObjects: set true to include all keys, i.e. object, array and string */
    public Map<String, Object> extractKeyPairs(JSONObject resource, Map<String, Object> keys, boolean includeNonObjects) {
        for (String key : resource.keySet()) {

            // get the value of the current key
            Object value = resource.get(key);

            // include all key types
            if (includeNonObjects) {
                // when any type, add key
                keys.put(key, value.toString());
            }

            // when object type, add key
            if (value instanceof JSONObject) {

                keys.put(key, value);
                extractKeyPairs((JSONObject) value, keys, includeNonObjects);
            }

            // when object or array type, add key
            if (value instanceof JSONArray) {
                // within this array, extract the object types
                keys.put(key, value);
                extractKeyPairs((JSONArray) value, keys, includeNonObjects);
            }

        }
        return keys;
    }

    /* private :: extracts json object types within the passed in array */
    private void extractKeyPairs(JSONArray resource, Map<String, Object> keys, boolean includeNonObjects) {
        for (int i = 0; i < resource.length(); i++) {

            Object value = resource.get(i);
            if (value instanceof JSONObject) {
                extractKeyPairs((JSONObject) value, keys, includeNonObjects);
            }
        }
    }

    /* create an instance of quote from a map entry */
    public Quote toQuoteFromMap(Map<String, String> quoteEntry) {
        return new Quote.QuoteBuilder()
                .withId(toInteger(quoteEntry.get(ID.label)))
                .withName(quoteEntry.get(NAME.label))
                .withSymbol(quoteEntry.get(SYMBOL.label))
                .withSlug(quoteEntry.get(SLUG.label))
                .withActive(toBoolean(quoteEntry.get(IS_ACTIVE.label)))
                .withFiat(toBoolean(quoteEntry.get(IS_FIAT.label)))
                .withRank(toDouble(quoteEntry.get(CMC_RANK.label)))
                .withNumMarketPairs(toInteger(quoteEntry.get(NUM_MARKET_PAIRS.label)))
                .withCirculatingSupply(toDouble(quoteEntry.get(CIRCULATING_SUPPLY.label)))
                .withMaxSupply(toDouble(quoteEntry.get(MAX_SUPPLY.label)))
                .withLastUpdated(toDateTime(quoteEntry.get(LAST_UPDATED.label)))
                .withDateAdded(toDateTime(quoteEntry.get(DATE_ADDED.label)))
                .withTags(toArrayFrom(quoteEntry.get(TAGS.label)))
                .withPrice(toDouble(quoteEntry.get(PRICE.label)))
                .withVolume24(toDouble(quoteEntry.get(VOLUME_24.label)))
                .withVolumeChange24(toDouble(quoteEntry.get(VOLUME_CHANGE_24.label)))
                .withPercentChangeHr(toFloat(quoteEntry.get(PERCENT_CHANGE_HR.label)))
                .withPercentChange24(toFloat(quoteEntry.get(PERCENT_CHANGE_24.label)))
                .withPercentChangeWk(toFloat(quoteEntry.get(PERCENT_CHANGE_7_DAY.label)))
                .withMarketCap(toDouble(quoteEntry.get(MARKET_CAP.label)))
                .withMarketCapDominance(toDouble(quoteEntry.get(MARKET_CAP_DOMINANCE.label)))
                .withFullyDilutedMarketCap(toDouble(quoteEntry.get(FULLY_DILUTED_MARKET_CAP.label)))
                .withLastUpdated(toDateTime(quoteEntry.get(LAST_UPDATED.label)))
                .build();
    }

}
