package org.crypto.quote;

import org.crypto.HttpException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static org.crypto.util.DataTypeUtil.*;
import static org.crypto.quote.QuoteLabel.*;
import static org.crypto.quote.QuoteLabel.LAST_UPDATED;

/* service class to fetch /listing resource from http://api.coinmarketcap.com api */
public class QuoteService {

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
