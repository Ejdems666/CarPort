package org.cba.components.table;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.cba.Path.PDF;
import static org.cba.Path.ROOT;

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
    public void testStringRow() {
        Row row = tableBuilder.createNewRow();
        row.addColumn("test value");
        Assert.assertEquals(row.toString(), "<tr><td>test value</td></tr>");
    }

    @Test
    public void testNonStringRow() {
        Row row = tableBuilder.createNewRow();
        row.addColumn(1);
        Assert.assertEquals(row.toString(), "<tr><td>1</td></tr>");
    }

    @Test
    public void testLinkColumn() {
        Row row = tableBuilder.createNewRow();
        row.addColumnLink("url", Row.Icon.EDIT);
        Assert.assertEquals(row.toString(), "<tr><td><a href='" + ROOT + "url'><i class=\"fa fa-" + Row.Icon.EDIT + "\" aria-hidden=\"true\"></i></a></td></tr>");
    }

    @Test
    public void testLinkColumnWithCustomPathSuffix() {
        Row row = tableBuilder.createNewRow();
        row.addColumnLink("url", Row.Icon.EDIT, PDF);
        Assert.assertEquals(row.toString(), "<tr><td><a href='" + PDF + "url'><i class=\"fa fa-" + Row.Icon.EDIT + "\" aria-hidden=\"true\"></i></a></td></tr>");
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
        Assert.assertEquals(tableBuilder.toString(), "<table class='test'></table>");
    }
}