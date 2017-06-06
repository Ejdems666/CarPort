package org.cba.components;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by adam on 24/05/2017.
 */
public class SelectBuilderTest {

    @Test
    public void testBareSelectWithoutAttributes() throws Exception {
        SelectBuilder selectBuilder = new SelectBuilder("label","name", 1);
        Assert.assertEquals(
                selectBuilder.toString(),
                "<label for='name'>label</label>" +
                        "<select id='name' class='selectpicker' name='name'></select>"
        );
    }

    @Test
    public void testBareSelectWithAttributes() throws Exception {
        SelectBuilder selectBuilder = new SelectBuilder("label","name", 1, "class='another'");
        Assert.assertEquals(
                selectBuilder.toString(),
                "<label for='name'>label</label>" +
                        "<select id='name' class='selectpicker' name='name' class='another'></select>"
        );
    }

    @Test
    public void testSelectOptions() throws Exception {
        SelectBuilder selectBuilder = new SelectBuilder("label","name", 1);
        selectBuilder.addOption(1,1);
        selectBuilder.addOption(2,2);
        Assert.assertEquals(
                selectBuilder.toString(),
                "<label for='name'>label</label>" +
                        "<select id='name' class='selectpicker' name='name'>" +
                        "<option selected value='1'>1</option>" +
                        "<option value='2'>2</option>" +
                        "</select>"
        );
    }
}