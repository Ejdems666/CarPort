package org.cba.parameter.exception;

import org.cba.parameter.ParameterMask;

/**
 * Created by adam on 19/04/2017.
 */
public class InvalidParameterTypeException extends ParameterParserException {
    public InvalidParameterTypeException(ParameterMask parameterMask, String actualValue) {
        super("Parameter " + parameterMask.getKey() + " is supposed to be of type " + parameterMask.getType() +
                " Which is incompatable with actuall value: " + actualValue);
    }
}
