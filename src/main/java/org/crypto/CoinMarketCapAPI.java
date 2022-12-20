package org.crypto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CoinMarketCapAPI {
    private static final String APIKEY = API_KEY.SECRET;

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

    public static List<Quote> getLatestQuotes(int limit) throws Exception {

        String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        String urlString = API_URL + "?limit=" + String.valueOf(limit);

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-CMC_PRO_API_KEY", APIKEY);

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Failed to get latest quotes: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        JSONArray dataArray = json.getJSONArray("data");

        List<Quote> result = new ArrayList<>();
        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject data = dataArray.getJSONObject(i);
            JSONObject quote = data.getJSONObject("quote");
            JSONObject quoteUSD = quote.getJSONObject("USD");
            Quote q = new Quote(
                    data.getInt("id"),
                    data.getString("name"),
                    data.getString("symbol"),
                    data.getString("slug"),
                    data.getInt("cmc_rank"),
                    quoteUSD.getDouble("price"),
                    quoteUSD.getDouble("volume_24h"),
                    quoteUSD.getDouble("market_cap"),
                    quoteUSD.getInt("market_cap_dominance"),
                    data.getDouble("circulating_supply"),
                    data.getDouble("total_supply"),
                    LocalDateTime.parse(quoteUSD.getString("last_updated").replace("Z", ""))
                    );
            result.add(q);
        }
        return result;
    }

}
