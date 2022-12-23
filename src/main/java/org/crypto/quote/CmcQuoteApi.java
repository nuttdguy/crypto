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

import static org.crypto.Util.toArrayFrom;
import static org.crypto.quote.QuoteLabel.*;
import static org.crypto.quote.QuoteLabel.LAST_UPDATED;

/* Coin Market Cap API options for v1 /listing & v2 /quotes endpoint */
public class CmcQuoteApi {

    private static final String APIKEY = API_KEY.SECRET;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    /* Returns the latest market quote for 1 or more cryptocurrencies */
    public List<Quote> getQuotes(int limit, int version) {
        // set min limit if 0 is passed in as value
        limit = limit == 0 ? 1 : limit;
        version = version == 1 ? 1 : 2;

        // endpoint for quote resource; expected json keys for response
        // v2 requires one or more comma separated ids of the tokens
        // v2/cryptocurrency/quotes/latest
        // v1/cryptocurrency/listings/latest
        final String RESOURCE_ENDPOINT = "v"+version+"/cryptocurrency/listings/latest";

        // set resource params and expected keys from the returns api response
        final String RESOURCE_PARAMS = "?limit=" + limit;
        final String JSON_KEY_1 = "data";

        // get the data from the resource
        StringBuilder apiResponse = fetchApiResource(RESOURCE_ENDPOINT, RESOURCE_PARAMS);

        // convert the response into a JSON object
        JSONObject jsonObject = new JSONObject(apiResponse.toString());

        // get the data array element from the response; extract nested object by key
        JSONArray jsonDataArray = jsonObject.getJSONArray(JSON_KEY_1);

        // transform the response into a json object, extract the data from the data key
        return toListFrom(jsonDataArray, version);
    }

    // to add -- swap string for array / list of keys
    /* compile a list of class instances from a json response */
    protected List<Quote> toListFrom(JSONArray jsonArray, int version) {

        List<Quote> dataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            // transform json objects into java class instances
            JSONObject jsonDataObject = jsonArray.getJSONObject(i);

            // feature to consider - handle multiple currency pairs
            Quote quote = toQuote(jsonDataObject, "USD");
            dataList.add(quote);
        }
        return dataList;
    }

    /* get data from the resource endpoint with params */
    protected StringBuilder fetchApiResource(String resourceEndpoint, String resourceParams)  {
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
    protected Quote toQuote(JSONObject jsonDataObject, String currencyKey) {
        // extract the quote from within the jsonData object
        JSONObject jsonQuoteObject = jsonDataObject.getJSONObject("quote");
        JSONObject jsonQuote = jsonQuoteObject.getJSONObject(currencyKey);


        return new Quote.QuoteBuilder()
                .withId(jsonDataObject.optInt(ID.label, 0))
                .withName(jsonDataObject.optString(NAME.label))
                .withSymbol(jsonDataObject.optString(SYMBOL.label))
                .withSlug(jsonDataObject.optString(SLUG.label))
                .withActive(jsonDataObject.optBoolean(IS_ACTIVE.label, true))
                .withFiat(jsonDataObject.optBoolean(IS_FIAT.label, false))
                .withRank(jsonDataObject.optInt(CMC_RANK.label, 0))
                .withNumMarketPairs(jsonDataObject.optInt(NUM_MARKET_PAIRS.label, 0))
                .withCirculatingSupply(jsonDataObject.optInt(CIRCULATING_SUPPLY.label, 0))
                .withMaxSupply(jsonDataObject.optDouble(MAX_SUPPLY.label, 0.00))
                .withLastUpdated(LocalDateTime.parse(jsonDataObject.getString(LAST_UPDATED.label).replace("Z", "")))
                .withDateAdded(LocalDateTime.parse(jsonDataObject.getString(DATE_ADDED.label).replace("Z", "")))
                .withTags(toArrayFrom(jsonDataObject.getJSONArray(TAGS.label)))
                .withPrice(jsonQuote.optDouble(PRICE.label, 0.00))
                .withVolume24(jsonQuote.optDouble(VOLUME_24.label, 0.00))
                .withVolumeChange24(jsonQuote.optDouble(VOLUME_CHANGE_24.label, 0.00))
                .withPercentChangeHr(jsonQuote.optFloat(PERCENT_CHANGE_HR.label))
                .withPercentChange24(jsonQuote.optFloat(PERCENT_CHANGE_24.label))
                .withPercentChangeWk(jsonQuote.optFloat(PERCENT_CHANGE_7_DAY.label))
                .withMarketCap(jsonQuote.optDouble(MARKET_CAP.label, 0.00))
                .withMarketCapDominance(jsonQuote.optInt(MARKET_CAP_DOMINANCE.label, 0))
                .withFullyDilutedMarketCap(jsonQuote.optDouble(FULLY_DILUTED_MARKET_CAP.label, 0.00))
                .withLastUpdated(LocalDateTime.parse(jsonQuote.optString(LAST_UPDATED.label).replace("Z", "")))
                .build();
    }

}
