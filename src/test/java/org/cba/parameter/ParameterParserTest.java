package org.cba.parameter;

import org.cba.parameter.exception.ParameterParserException;
import org.cba.parameter.exception.RequiredParameterNonExistentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * Created by adam on 19/04/2017.
 */
public class ParameterParserTest {
    private ParameterParser parameterParser;
    private ParameterSieve parameterSieve;
    private ParsedParameters parsedParameters;
    HttpServletRequest req;

    @BeforeMethod
    public void setUp() throws Exception {
        parameterParser = new ParameterParser();
        parameterSieve = new ParameterSieve();
        parsedParameters = null;
        req = createMock(HttpServletRequest.class);
    }

    private void parseParameters() {
        replay(req);
        try {
            parsedParameters = parameterParser.parseParameters(req, parameterSieve);
        } catch (ParameterParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringParameter() {
        parameterSieve.addString("key");
        expect(req.getParameter("key")).andReturn("value");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), "value");
    }

    @Test
    public void testIntegerParameter() {
        parameterSieve.addInteger("key");
        expect(req.getParameter("key")).andReturn("1");
        parseParameters();
        Assert.assertEquals(parsedParameters.getInteger("key"), Integer.valueOf(1));
    }

    @Test
    public void testBooleanParameter() {
        parameterSieve.addBoolean("key");
        expect(req.getParameter("key")).andReturn("1");
        parseParameters();
        Assert.assertTrue(parsedParameters.getBoolean("key"));
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredNonExistentParameter() throws ParameterParserException {
        parameterSieve.addString("key").setRequired();
        parsedParameters = parameterParser.parseParameters(req, parameterSieve);
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredEmptyParameter() throws ParameterParserException {
        parameterSieve.addString("key").setRequired();
        expect(req.getParameter("key")).andReturn("");
        parsedParameters = parameterParser.parseParameters(req, parameterSieve);
    }

    @Test
    public void testCancelRequire() {
        ParameterMask mask = parameterSieve.addString("key");
        mask.setRequired();
        mask.setRequired(false);
        expect(req.getParameter("key")).andReturn("");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), null);
    }
}
