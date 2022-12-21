package org.crypto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.crypto.model.Quote;
import org.crypto.model.TokenInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.System.out;
import static org.crypto.enums.QuoteEnum.*;

public class CoinMarketCapAPI {
    private static final String APIKEY = API_KEY.SECRET;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    public static List<OHLCVRecord> getPriceHistory(String symbol, int limit) throws Exception {
        String API_URL = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/ohlcv/historical";

        String urlString = API_URL + "?symbol=" + symbol + "&limit=" + limit;

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-CMC_PRO_API_KEY", APIKEY);

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Failed to get price history: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        JSONArray records = json.getJSONArray("data");

        List<OHLCVRecord> result = new ArrayList<>();
        for (int i = 0; i < records.length(); i++) {
            JSONObject record = records.getJSONObject(i);
            OHLCVRecord ohlcvRecord = new OHLCVRecord(
                    record.getLong("timestamp"),
                    record.getDouble("open"),
                    record.getDouble("high"),
                    record.getDouble("low"),
                    record.getDouble("close"),
                    record.getDouble("volume")
            );
            result.add(ohlcvRecord);
        }

        return result;
    }

    /* Returns the latest market quote for 1 or more cryptocurrencies */
    public static List<Quote> getQuotes(int limit, int version) {
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
        StringBuilder response = getResource(RESOURCE_ENDPOINT, RESOURCE_PARAMS);

        // transform the response into a json object, extract the data from the data key
        return toListFrom(response, version, JSON_KEY_1, JSON_KEY_2);
    }

    // to add -- swap string for array / list of keys
    /* compile a list of class instances from a json response */
    private static List<Quote> toListFrom(StringBuilder response, int version, String jsonKey1, String jsonKey2) {
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray jsonArray = jsonObject.getJSONArray(jsonKey1);
        List<Quote> dataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            // get the data array element from the response; extract nested object by key
            JSONObject jsonData = jsonArray.getJSONObject(i);
            JSONObject jsonQuote = jsonData.getJSONObject(jsonKey2);

            // transform json objects into java class instances
            TokenInfo tokenInfo = toTokenInfo(jsonData, version);
            Quote q = toQuote(tokenInfo, version, jsonQuote, "USD");
            dataList.add(q);
        }
        return dataList;
    }

    /* get data from the resource endpoint with params */
    private static StringBuilder getResource(String resourceEndpoint, String resourceParams)  {
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
    private static Quote toQuote(TokenInfo tokenInfo, int version, JSONObject jsonObject, String currencyKey) {
        JSONObject jsonQuote = jsonObject.getJSONObject(currencyKey);

        if (version == 1) {
            return new Quote(
                    tokenInfo,
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
        return new Quote(
                tokenInfo,
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
    private static TokenInfo toTokenInfo(JSONObject jsonObject, int version) {
        JSONArray jsonArray = jsonObject.getJSONArray(TAGS.label);

        String[] tags = Stream.of(jsonArray)
                .map(JSONArray::toString)
                .toArray(String[]::new);

        if (version == 1) {
            return new TokenInfo(
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
        return new TokenInfo(
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
                tags;
    }

}
