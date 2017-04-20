package org.cba.parameter;

/**
 * Created by adam on 19/04/2017.
 */
public class ParameterMask {
    private final String key;
    private final Class<?> type;
    private boolean required = false;

    public ParameterMask(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    public ParameterMask setRequired() {
        this.required = true;
        return this;
    }

    public ParameterMask setRequired(boolean required) {
        this.required = required;
        return this;
    }
}
