package org.crypto.quote;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuoteServiceTest {

    QuoteService quoteService;

    JSONObject jsonObjectUnderTest;

    @BeforeEach
    void init() {
        quoteService = new QuoteService();
        jsonObjectUnderTest = new JSONObject("{\"data\":[{\"id\":1,\"name\":\"Bitcoin\",\"symbol\":\"BTC\",\"slug\":\"bitcoin\",\"cmc_rank\":5,\"num_market_pairs\":500,\"circulating_supply\":16950100,\"total_supply\":16950100,\"max_supply\":21000000,\"last_updated\":\"2018-06-02T22:51:28.209Z\",\"date_added\":\"2013-04-28T00:00:00.000Z\",\"tags\":[\"mineable\"],\"platform\":null,\"self_reported_circulating_supply\":null,\"self_reported_market_cap\":null,\"quote\":{\"USD\":{\"price\":9283.92,\"volume_24h\":7155680000,\"volume_change_24h\":-0.152774,\"percent_change_1h\":-0.152774,\"percent_change_24h\":0.518894,\"percent_change_7d\":0.986573,\"market_cap\":852164659250.2758,\"market_cap_dominance\":51,\"fully_diluted_market_cap\":952835089431.14,\"last_updated\":\"2018-08-09T22:53:32.000Z\"},\"BTC\":{\"price\":1,\"volume_24h\":772012,\"volume_change_24h\":0,\"percent_change_1h\":0,\"percent_change_24h\":0,\"percent_change_7d\":0,\"market_cap\":17024600,\"market_cap_dominance\":12,\"fully_diluted_market_cap\":952835089431.14,\"last_updated\":\"2018-08-09T22:53:32.000Z\"}}},{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"num_market_pairs\":6360,\"circulating_supply\":16950100,\"total_supply\":16950100,\"max_supply\":21000000,\"last_updated\":\"2018-06-02T22:51:28.209Z\",\"date_added\":\"2013-04-28T00:00:00.000Z\",\"tags\":[\"mineable\"],\"platform\":null,\"quote\":{\"USD\":{\"price\":1283.92,\"volume_24h\":7155680000,\"volume_change_24h\":-0.152774,\"percent_change_1h\":-0.152774,\"percent_change_24h\":0.518894,\"percent_change_7d\":0.986573,\"market_cap\":158055024432,\"market_cap_dominance\":51,\"fully_diluted_market_cap\":952835089431.14,\"last_updated\":\"2018-08-09T22:53:32.000Z\"},\"ETH\":{\"price\":1,\"volume_24h\":772012,\"volume_change_24h\":-0.152774,\"percent_change_1h\":0,\"percent_change_24h\":0,\"percent_change_7d\":0,\"market_cap\":17024600,\"market_cap_dominance\":12,\"fully_diluted_market_cap\":952835089431.14,\"last_updated\":\"2018-08-09T22:53:32.000Z\"}}}],\"status\":{\"timestamp\":\"2018-06-02T22:51:28.209Z\",\"error_code\":0,\"error_message\":\"\",\"elapsed\":10,\"credit_count\":1}}");
    }

    @Test
    void extractJsonArrayTest() {
        JSONArray jsonArray = quoteService.extractJsonArray(new JSONObject("{\"data\":[{\"id\":1}]}"), "data");

        assertNotNull(jsonArray);
        assertTrue(jsonArray.length() > 0);
    }

    @Test
    void extractJsonObjectTest() {
        JSONObject jsonObject = quoteService.extractJsonObject(new JSONObject("{\"data\":{\"id\":1}}"), "data");

        assertNotNull(jsonObject);
    }

    @Test
    void extractKeyPairsTest() {
        Map<String, Object> keyList = new HashMap<>();
        keyList = quoteService.extractKeyPairs(jsonObjectUnderTest, keyList, true);

        assertTrue(keyList.size() > 0);
    }

    @Test
    void mapObjectsToStringTest() {
        // setup
        Map<String, Object> keyList = new HashMap<>();
        keyList = quoteService.extractKeyPairs(jsonObjectUnderTest, keyList, true);

        // under test
        Map<String, String> mapEntry = quoteService.mapObjectsToString(keyList);

        assertTrue(mapEntry.size() > 0);
        assertTrue(mapEntry.containsKey("id"));
        assertTrue(mapEntry.containsKey("tags"));
        assertTrue(Double.parseDouble(mapEntry.get("circulating_supply")) > 0);

    }

    @Test
    void toQuoteFromMapTest() {
        // setup
        Map<String, Object> keyList = new HashMap<>();
        keyList = quoteService.extractKeyPairs(jsonObjectUnderTest, keyList, true);

        Map<String, String> mapEntry = quoteService.mapObjectsToString(keyList);

        // under test
        Quote quote = quoteService.toQuoteFromMap(mapEntry);

        assertNotNull(quote);
        assertEquals("Ethereum", quote.getName());
        assertTrue(mapEntry.containsKey("tags"));
    }


}