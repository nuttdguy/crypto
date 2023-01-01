package org.crypto.report;

import org.crypto.IReportService;
import org.crypto.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class ReportService implements IReportService {


    @Override
    public List<Map<String, String>> createMappedEntriesFrom(String[] headerRow, String[] fileContent) {
        return null;
    }

    @Override
    public Transaction createTxTransaction(JSONObject jsonObject, String actionType) {
        return null;
    }

    @Override
    public Transaction createTxTransaction(Map<String, String> mapEntry, String actionType) {
        return null;
    }

    @Override
    public List<Transaction> createTransactionListFrom(JSONArray resourceArray, String actionType) {
        return null;
    }

    @Override
    public InputStream fetchApiResource(String resourceUrl, String resourceParams, String APIKEY) throws IOException {
        return null;
    }

    @Override
    public JSONArray extractJsonArrayFrom(InputStream resourceStream, String resourceObjectKey) throws JSONException, IOException {
        return null;
    }

    @Override
    public List<String> readTransactionsFrom(String file) throws IOException {
        return null;
    }

    @Override
    public int writeTransactionsToCsv(List<Transaction> transactions, String fileName, boolean append) {
        return 0;
    }
}
