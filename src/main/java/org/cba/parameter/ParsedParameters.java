package org.cba.parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 19/04/2017.
 */
public class ParsedParameters {
    private Map<String,Object> parameters = new HashMap<>();

    public void addParameter(String key, ParsedParameter parameter) {
        parameters.put(key,getValue(parameter));
    }

    public String getString(String key) {
        return ((String) parameters.get(key));
    }
    public Integer getInteger(String key) {
        return ((Integer) parameters.get(key));
    }
    public Boolean getBoolean(String key) {
        return ((Boolean) parameters.get(key));
    }
    private Object getValue(ParsedParameter parameter) {
        if (parameter.getValue() == null) {
            return parameter.getDefaultValue();
        }
        return parameter.getValue();
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
