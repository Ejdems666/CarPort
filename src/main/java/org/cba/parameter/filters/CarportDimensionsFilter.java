package org.cba.parameter.filters;

import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adam on 26/05/2017.
 */
public class CarportDimensionsFilter {
    public static ParsedParameters getParameters(HttpServletRequest request) throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("frameWidth").setRequired();
        parameterFilter.addInteger("frameLength").setRequired();
        return parameterFilter.parseParameters(request);
    }
}
