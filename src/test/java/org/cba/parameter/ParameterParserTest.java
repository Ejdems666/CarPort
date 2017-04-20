package org.cba.parameter;

import org.cba.parameter.exception.ParameterParserException;
import org.cba.parameter.exception.RequiredParameterNonExistentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 19/04/2017.
 */
public class ParameterParserTest {
    private ParameterParser parameterParser;
    private ParameterSieve parameterSieve;
    private ParsedParameters parsedParameters;
    private Map values;

    @BeforeMethod
    public void setUp() throws Exception {
        parameterParser = new ParameterParser();
        parameterSieve = new ParameterSieve();
        parsedParameters = null;
        values = new HashMap();
    }

    private void parseParameters() {
        try {
            parsedParameters = parameterParser.parseParameters(values, parameterSieve);
        } catch (ParameterParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringParameter() {
        parameterSieve.addString("key");
        values.put("key", "value");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), "value");
    }

    @Test
    public void testIntegerParameter() {
        parameterSieve.addNumber("key");
        values.put("key", "1");
        parseParameters();
        Assert.assertEquals(parsedParameters.getInteger("key"), Integer.valueOf(1));
    }

    @Test
    public void testBooleanParameter() {
        parameterSieve.addBoolean("key");
        values.put("key", "1");
        parseParameters();
        Assert.assertTrue(parsedParameters.getBoolean("key"));
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredNonExistentParameter() throws ParameterParserException {
        parameterSieve.addString("key").setRequired();
        parsedParameters = parameterParser.parseParameters(values, parameterSieve);
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredEmptyParameter() throws ParameterParserException {
        parameterSieve.addString("key").setRequired();
        values.put("key", "");
        parsedParameters = parameterParser.parseParameters(values, parameterSieve);
    }

    @Test
    public void testCancelRequire() {
        ParameterMask mask = parameterSieve.addString("key");
        mask.setRequired();
        mask.setRequired(false);
        values.put("key", "");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), null);
    }
}
