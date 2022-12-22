package org.crypto.quote;

import org.crypto.API_KEY;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.crypto.quote.QuoteLabel.*;
import static org.crypto.quote.QuoteLabel.LAST_UPDATED;

/* Coin Market Cap API options for v1 /listing & v2 /quotes endpoint */
public class CmcQuoteApi {

    private static final String APIKEY = API_KEY.SECRET;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    /* Returns the latest market quote for 1 or more cryptocurrencies */
    public static List<QuoteDetail> getQuotes(int limit, int version) {
        // set min limit if 0 is passed in as value
        limit = limit == 0 ? 1 : limit;
        version = version != 1 ? 2 : version;

        // endpoint for quote resource; expected json keys for response
        // v2 requires one or more comma separated ids of the tokens
        // v2/cryptocurrency/quotes/latest
        // v1/cryptocurrency/listings/latest
        final String RESOURCE_ENDPOINT = "v"+version+"/cryptocurrency/listings/latest";

        // set resource params and expected keys from the returns api response
        final String RESOURCE_PARAMS = "?limit=" + limit;
        final String JSON_KEY_1 = "data";
        final String JSON_KEY_2 = "quote";

        // get the data from the resource
        StringBuilder apiResponse = fetchApiResource(RESOURCE_ENDPOINT, RESOURCE_PARAMS);

        // transform the response into a json object, extract the data from the data key
        return toListFrom(apiResponse, version, JSON_KEY_1, JSON_KEY_2);
    }

    // to add -- swap string for array / list of keys
    /* compile a list of class instances from a json response */
    private static List<QuoteDetail> toListFrom(StringBuilder apiResponse, int version, String jsonKey1, String jsonKey2) {
        JSONObject jsonObject = new JSONObject(apiResponse.toString());
        JSONArray jsonArray = jsonObject.getJSONArray(jsonKey1);
        List<QuoteDetail> dataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            // get the data array element from the response; extract nested object by key
            JSONObject jsonData = jsonArray.getJSONObject(i);
            JSONObject jsonQuote = jsonData.getJSONObject(jsonKey2);

            // transform json objects into java class instances
            Quote quote = toQuote(jsonData, version);
            QuoteDetail q = toQuoteDetail(quote, version, jsonQuote, "USD");
            dataList.add(q);
        }
        return dataList;
    }

    /* get data from the resource endpoint with params */
    private static StringBuilder fetchApiResource(String resourceEndpoint, String resourceParams)  {
        // create url and open connection
        URL url;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        StringBuilder response = new StringBuilder();

        try {
            url = new URL(BASE_URL + resourceEndpoint + resourceParams);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // set http header params
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-CMC_PRO_API_KEY", APIKEY);

            // validate response for url returns a valid response
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("HTTP Response: " + responseCode);
            }

            // send request to get response as a stream; read and then close the reader by default
            inputStream = httpURLConnection.getInputStream();

            String line;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /* create an instance of quote from a json object */
    private static QuoteDetail toQuoteDetail(Quote quote, int version, JSONObject jsonObject, String currencyKey) {
        JSONObject jsonQuote = jsonObject.getJSONObject(currencyKey);

        if (version == 1) {
            return new QuoteDetail(
                    quote,
                    jsonQuote.optDouble(PRICE.label, 0),
                    jsonQuote.optDouble(VOLUME_24.label, 0),
                    jsonQuote.optDouble(VOLUME_CHANGE_24.label, 0),
                    jsonQuote.optFloat(PERCENT_CHANGE_HR.label, 0),
                    jsonQuote.optFloat(PERCENT_CHANGE_24.label, 0),
                    jsonQuote.optFloat(PERCENT_CHANGE_7_DAY.label, 0),
                    jsonQuote.optDouble(MARKET_CAP.label, 0),
                    jsonQuote.optInt(MARKET_CAP_DOMINANCE.label, 0),
                    jsonQuote.optDouble(FULLY_DILUTED_MARKET_CAP.label, 0),
                    LocalDateTime.parse(jsonQuote.getString("last_updated").replace("Z", "")));
        }

        // for api version 2
        return new QuoteDetail(
                quote,
                jsonQuote.optDouble(PRICE.label, 0),
                jsonQuote.optDouble(VOLUME_24.label, 0),
                jsonQuote.optDouble(VOLUME_CHANGE_24.label, 0),
                jsonQuote.optFloat(PERCENT_CHANGE_HR.label, 0),
                jsonQuote.optFloat(PERCENT_CHANGE_24.label, 0),
                jsonQuote.optFloat(PERCENT_CHANGE_7_DAY.label, 0),
                jsonQuote.optFloat(PERCENT_CHANGE_30_DAY.label, 0),
                jsonQuote.optDouble(MARKET_CAP.label, 0),
                jsonQuote.optInt(MARKET_CAP_DOMINANCE.label, 0),
                jsonQuote.optDouble(FULLY_DILUTED_MARKET_CAP.label, 0),
                LocalDateTime.parse(jsonQuote.getString("last_updated").replace("Z", "")));
    }

    /* create an instance of tokenInfo from a json object */
    private static Quote toQuote(JSONObject jsonObject, int version) {
        String[] tags = toArrayFrom(jsonObject.getJSONArray(TAGS.label));

        if (version == 1) {
            return new Quote(
                    jsonObject.getInt(ID.label),
                    jsonObject.getString(NAME.label),
                    jsonObject.getString(SYMBOL.label),
                    jsonObject.getString(SLUG.label),
                    jsonObject.optInt(CMC_RANK.label, 0),
                    jsonObject.optInt(NUM_MARKET_PAIRS.label, 0),
                    jsonObject.optInt(CIRCULATING_SUPPLY.label, 0),
                    jsonObject.optDouble(MAX_SUPPLY.label, 0),
                    LocalDateTime.parse(jsonObject.getString(LAST_UPDATED.label).replace("Z", "")),
                    LocalDateTime.parse(jsonObject.getString(DATE_ADDED.label).replace("Z", "")),
                    tags
            );
        }

        // for api version 2
        return new Quote(
                jsonObject.getInt(ID.label),
                jsonObject.getString(NAME.label),
                jsonObject.getString(SYMBOL.label),
                jsonObject.getString(SLUG.label),
                jsonObject.getBoolean(IS_ACTIVE.label),
                jsonObject.getBoolean(IS_FIAT.label),
                jsonObject.optInt(CIRCULATING_SUPPLY.label, 0),
                jsonObject.optDouble(MAX_SUPPLY.label, 0),
                LocalDateTime.parse(jsonObject.getString(DATE_ADDED.label).replace("Z", "")),
                jsonObject.optInt(NUM_MARKET_PAIRS.label, 0),
                jsonObject.optInt(CMC_RANK.label, 0),
                LocalDateTime.parse(jsonObject.getString(LAST_UPDATED.label).replace("Z", "")),
                tags
        );
    }

    private static String[] toArrayFrom(JSONArray jsonArray) {
        return Stream.of(jsonArray)
                .map(JSONArray::toString)
                .toArray(String[]::new);
    }

}
