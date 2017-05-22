package org.cba.parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 19/04/2017.
 */
public class ParameterFilter {
    private Map<String, ParameterMask> parameters = new HashMap<>();

    public ParameterMask addInteger(String key) {
        ParameterMask mask = new ParameterMask(key, Integer.class);
        parameters.put(key, mask);
        return mask;
    }

    public ParameterMask addString(String key) {
        ParameterMask mask = new ParameterMask(key, String.class);
        parameters.put(key, mask);
        return mask;
    }

    public ParameterMask addBoolean(String key) {
        ParameterMask mask = new ParameterMask(key, Boolean.class);
        parameters.put(key, mask);
        return mask;
    }

    public Map<String, ParameterMask> getParameters() {
        return parameters;
    }
}
