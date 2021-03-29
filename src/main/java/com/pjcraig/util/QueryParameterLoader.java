package com.pjcraig.util;

import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;

public interface QueryParameterLoader {
    /**
     * Converts the provided multivalued map of query parameters into a single-value map consisting of the first
     * query parameter.
     * @param map The MultivaluedMap to convert.
     * @return The new Map consisting of a single value for every key.
     */
    default Map<String, String> getParameterMap(MultivaluedMap<String, String> map) {
        Map<String, String> parameters = new HashMap<>();
        for (String key : map.keySet()) {
            parameters.put(key, map.getFirst(key));
        }
        return parameters;
    }
}
