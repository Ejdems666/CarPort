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
    private ParameterFilter parameterFilter;
    private ParsedParameters parsedParameters;
    HttpServletRequest request;

    @BeforeMethod
    public void setUp() throws Exception {
        parameterParser = new ParameterParser();
        parameterFilter = new ParameterFilter();
        parsedParameters = null;
        request = createMock(HttpServletRequest.class);
    }

    private void parseParameters() {
        replay(request);
        try {
            parsedParameters = parameterParser.parseParameters(request, parameterFilter);
        } catch (ParameterParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringParameter() {
        parameterFilter.addString("key");
        expect(request.getParameter("key")).andReturn("value");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), "value");
    }

    @Test
    public void testIntegerParameter() {
        parameterFilter.addInteger("key");
        expect(request.getParameter("key")).andReturn("1");
        parseParameters();
        Assert.assertEquals(parsedParameters.getInteger("key"), Integer.valueOf(1));
    }

    @Test
    public void testBooleanParameter() {
        parameterFilter.addBoolean("key");
        expect(request.getParameter("key")).andReturn("1");
        parseParameters();
        Assert.assertTrue(parsedParameters.getBoolean("key"));
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredNonExistentParameter() throws ParameterParserException {
        parameterFilter.addString("key").setRequired();
        parsedParameters = parameterParser.parseParameters(request, parameterFilter);
    }

    @Test(expectedExceptions = RequiredParameterNonExistentException.class)
    public void testRequiredEmptyParameter() throws ParameterParserException {
        parameterFilter.addString("key").setRequired();
        expect(request.getParameter("key")).andReturn("");
        parsedParameters = parameterParser.parseParameters(request, parameterFilter);
    }

    @Test
    public void testCancelRequire() {
        ParameterMask mask = parameterFilter.addString("key");
        mask.setRequired();
        mask.setRequired(false);
        expect(request.getParameter("key")).andReturn("");
        parseParameters();
        Assert.assertEquals(parsedParameters.getString("key"), null);
    }
}
