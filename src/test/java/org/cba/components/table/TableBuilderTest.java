package org.cba.components.table;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by adam on 20/05/2017.
 */
public class TableBuilderTest {
    private TableBuilder tableBuilder;

    @BeforeMethod
    public void setUp() throws Exception {
        tableBuilder = new TableBuilder();
    }

    @Test
    public void testTableBuilderAssemblyWithHeader() throws Exception {
        tableBuilder.addHeader("test", "one,two");
        Assert.assertEquals(
                tableBuilder.toString(),
                "<table>" +
                        "<tr><th colspan='2'>test</th></tr>" +
                        "<tr><th>one</th><th>two</th></tr>" +
                        "</table>"
        );
    }

    @Test
    public void testRow() {
        Row row = tableBuilder.createNewRow();
        row.addColumn("test value");
        Assert.assertEquals(row.toString(), "<tr><td>test value</td></tr>");
    }

    @Test
    public void testTableBuilderAssemblyWithHeaderAndData() {
        tableBuilder.addHeader("test", "one");
        tableBuilder.createNewRow().addColumn("test");
        Assert.assertEquals(
                tableBuilder.toString(),
                "<table>" +
                        "<tr><th colspan='1'>test</th></tr>" +
                        "<tr><th>one</th></tr>" +
                        "<tr><td>test</td></tr>" +
                        "</table>"
        );
    }

    @Test
    public void testTableWithCSSClass() {
        tableBuilder = new TableBuilder("test");
        Assert.assertEquals(tableBuilder.toString(),"<table class='test'></table>");
    }
}