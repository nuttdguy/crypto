package org.crypto.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/* Util class to translate data types into json or string formats */
public class JsonUtil {

    /* read input stream; output string to JsonObject */
    public static JSONObject toJsonObject(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return new JSONObject(sb.toString());
    }

    public static JSONObject toJsonObject(Map<String, Object> mapEntry) {
        return new JSONObject(mapEntry);
    }

    /* transform json native object values types, e.g. Integer, BigDecimal, Array -> String, into String format */
    public static Map<String, String> mapObjectsToString(Map<String, Object> resourceKeys) {
        Map<String, String> mapEntries = new HashMap<>();
        for (var entry : resourceKeys.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject)) {
                mapEntries.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return mapEntries;
    }

    public static Map<String, String> mapObjectsToString(JSONObject resourceKeys) {
        Map<String, String> mapEntries = new HashMap<>();
        for (var key : resourceKeys.keySet()) {
            mapEntries.put(key, (String) resourceKeys.get(key));
        }
        return mapEntries;
    }

    /* extract json array by object key */
    public static JSONArray extractJsonArray(JSONObject resource, String key) {
        return resource.getJSONArray(key);
    }

    /* extract json object by object key */
    public static JSONObject extractJsonObject(JSONObject resource, String key) {
        return resource.getJSONObject(key);
    }

    /* extract all objects and array key types; optional -> include other native type, e.g. Integer, BigDecimal
     *  resource: the json object
     *  keys: an empty map for adding String key and Object
     *  includeNonObjects: set true to include all keys, i.e. object, array and string */
    public static Map<String, Object> extractKeyAndJsonType(JSONObject resource, Map<String, Object> keys, boolean includeNonObjects) {
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
                extractKeyAndJsonType((JSONObject) value, keys, includeNonObjects);
            }

            // when object or array type, add key
            if (value instanceof JSONArray) {
                // within this array, extract the object types
                keys.put(key, value);
                extractKeyAndJsonType((JSONArray) value, keys, includeNonObjects);
            }

        }
        return keys;
    }

    /* private :: extracts json object types within the passed in array */
    private static void extractKeyAndJsonType(JSONArray resource, Map<String, Object> keys, boolean includeNonObjects) {
        for (int i = 0; i < resource.length(); i++) {

            Object value = resource.get(i);
            if (value instanceof JSONObject) {
                extractKeyAndJsonType((JSONObject) value, keys, includeNonObjects);
            }
        }
    }


}
