package org.crypto.ohlcv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.crypto.API_KEY;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.crypto.ohlcv.OhlcvLabel.*;

/* Coin Market Cap API options for v2 /ohlcv endpoint - requires startup subscription */
/* TODO IMPLEMENT MOCK TESTS */
public class CmcOhlcvApi {
    private static final String APIKEY = API_KEY.CMC_SECRET;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    /* Endpoint requires hobbyist  */
    public static List<OhlcvQuote> getPriceHistory(String symbol, int limit) throws Exception {
        // set min limit if 0 is passed in as value
        limit = limit == 0 ? 1 : limit;

        final String RESOURCE_ENDPOINT = "v2/cryptocurrency/listings/latest";

        // set resource params and expected keys from the returns api response
        final String RESOURCE_PARAMS = "?limit=" + limit;
        final String JSON_KEY_1 = "data";
        final String JSON_KEY_2 = "quote";

        // get the data from the resource
        StringBuilder apiResponse = fetchApiResource(RESOURCE_ENDPOINT, RESOURCE_PARAMS);

        // transform the response into a json object, extract the data from the data key
        return toListFrom(apiResponse, JSON_KEY_1, JSON_KEY_2);
    }

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

    private static List<OhlcvQuote> toListFrom(StringBuilder apiResponse, String jsonKey1, String jsonKey2) {
        JSONObject jsonObject = new JSONObject(apiResponse.toString());
        JSONArray jsonArray = jsonObject.getJSONArray(jsonKey1);
        List<OhlcvQuote> dataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            // get the data array element from the response; extract nested object by key
            JSONObject jsonData = jsonArray.getJSONObject(i);
            JSONObject jsonQuote = jsonData.getJSONObject(jsonKey2);

            // transform json objects into java class instances
            Ohlcv ohlcv = toOhlc(jsonData);
            OhlcvQuote ohlcvQuote = toOhlcQuote(ohlcv, jsonQuote, "USD");

            dataList.add(ohlcvQuote);
        }
        return dataList;
    }

    private static OhlcvQuote toOhlcQuote(Ohlcv ohlcv, JSONObject jsonObject, String currencyKey) {
        JSONObject jsonQuote = jsonObject.getJSONObject(currencyKey);

        return new OhlcvQuote(
                ohlcv,
                jsonQuote.optDouble(OPEN.label, 0),
                jsonQuote.optDouble(HIGH.label, 0),
                jsonQuote.optDouble(LOW.label, 0),
                jsonQuote.optDouble(CLOSE.label, 0),
                jsonQuote.optDouble(VOLUME.label, 0),
                jsonQuote.optDouble(MARKET_CAP.label, 0),
                toLocalDateTime(jsonQuote, TIMESTAMP.label),
                toLocalDateTime(jsonQuote, TIME_OPEN.label),
                toLocalDateTime(jsonQuote, TIME_CLOSE.label),
                toLocalDateTime(jsonQuote, TIME_HIGH.label),
                toLocalDateTime(jsonQuote, TIME_LOW.label)
        );
    }

    private static LocalDateTime toLocalDateTime(JSONObject jsonObject, String label) {
        String dateTimeString = jsonObject.optString(label, "");
        if (!Objects.equals(dateTimeString, ""))
            return LocalDateTime.parse(dateTimeString.replace("Z", ""));
        return null;
    }

    private static Ohlcv toOhlc(JSONObject jsonObject) {
        return new Ohlcv(
                jsonObject.optInt(ID.label, 0),
                jsonObject.optString(NAME.label, ""),
                jsonObject.optString(SYMBOL.label, "")
        );
    }

}
