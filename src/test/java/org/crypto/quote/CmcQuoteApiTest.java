package org.crypto.quote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CmcQuoteApiTest {

    CmcQuoteApi cmcQuoteApi;

    @BeforeEach
    void init() {
        cmcQuoteApi = new CmcQuoteApi();
    }

    @Test
    void testGetQuotes() {
        List<Quote> result = cmcQuoteApi.getQuotes(0, 0);
        Assertions.assertEquals(List.of(null), result);
    }

    @Test
    void testToListFrom() {
        List<Quote> result = cmcQuoteApi.toListFrom(null, 0);
        Assertions.assertEquals(List.of(null), result);
    }

    @Test
    void testFetchApiResource() {
        StringBuilder result = cmcQuoteApi.fetchApiResource("resourceEndpoint", "resourceParams");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testToQuote() {
        Quote result = cmcQuoteApi.toQuote(null, "currencyKey");
        Assertions.assertEquals(null, result);
    }
}
