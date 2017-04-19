package org.cba.parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 19/04/2017.
 */
public class ParsedParameters {
    private Map<String,Object> values = new HashMap<>();

    public void addString(String key, String value) {
        values.put(key,value);
    }
    public void addInteger(String key, Integer value) {
        values.put(key,value);
    }
    public void addBoolean(String key, Boolean value) {
        values.put(key,value);
    }

    public String getString(String key) {
        return ((String) values.get(key));
    }
    public Integer getInteger(String key) {
        return ((Integer) values.get(key));
    }
    public Boolean getBoolean(String key) {
        return ((Boolean) values.get(key));
    }

    public Map<String, Object> getValues() {
        return values;
    }
}
