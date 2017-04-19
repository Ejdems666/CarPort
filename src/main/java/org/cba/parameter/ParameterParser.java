package org.cba.parameter;

import org.cba.parameter.exception.InvalidParameterTypeException;
import org.cba.parameter.exception.ParameterParserException;
import org.cba.parameter.exception.RequiredParameterNonExistentException;

import java.util.Map;

/**
 * Created by adam on 19/04/2017.
 */
public class ParameterParser {
    public ParsedParameters parseParameters(Map sendParameters, ParameterSieve mask) throws ParameterParserException {
        ParsedParameters parsedParameters = new ParsedParameters();
        Map<String, ParameterMask> parameters = mask.getParameters();
        for (ParameterMask parameterMask : parameters.values()) {
            String key = parameterMask.getKey();
            if (sendParameters.containsKey(key)) {
                String sendValue = sendParameters.get(key).toString();
                if (!sendValue.isEmpty()) {
                    Class<?> type = parameterMask.getType();
                    if (type.equals(String.class)) {
                        parsedParameters.addString(key, sendValue);
                    } else if (type.equals(Integer.class)) {
                        parsedParameters.addInteger(key, parseInteger(sendValue, parameterMask));
                    } else if (type.equals(Boolean.class)) {
                        parsedParameters.addBoolean(key, parseBoolean(sendValue, parameterMask));
                    }
                } else if (parameterMask.isRequired()) {
                    throw new RequiredParameterNonExistentException(key);
                }
            } else if (parameterMask.isRequired()) {
                throw new RequiredParameterNonExistentException(key);
            }
        }
        return parsedParameters;
    }

    private Integer parseInteger(String sendValue, ParameterMask parameterMask) throws InvalidParameterTypeException {
        Integer sendIntValue;
        try {
            sendIntValue = Integer.parseInt(sendValue);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(parameterMask, sendValue);
        }
        return sendIntValue;
    }

    private Boolean parseBoolean(String sendValue, ParameterMask parameterMask) throws InvalidParameterTypeException {
        if (sendValue.equals("0")) {
            return false;
        } else if (sendValue.equals("1")) {
            return true;
        } else {
            throw new InvalidParameterTypeException(parameterMask, sendValue);
        }
    }
}
