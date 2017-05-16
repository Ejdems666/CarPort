package org.cba.model.carport.formating;

import org.cba.domain.Carport;
import org.cba.model.carport.calculation.BareFrameMaterialCalculator;
import org.cba.model.carport.calculation.FrameMaterialCalculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by adam on 16/05/2017.
 */
public class PartsTableFormatterTest {
    private Carport carport;
    private PartRecordsFormatter formatter;

    @BeforeMethod
    public void setUp() {
        carport = Carport.find.byId(1);
    }

    @Test
    public void testGetPartsTable() throws Exception {
        formatter = new PartsTableFormatter();
        int desiredLength = carport.getDefaultLength();
        int desiredWidth = carport.getDefaultWidth();
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, desiredLength);
        formatter.addPartRecord(calculator.getFrontAndBackUpperPillars());
        Assert.assertEquals(
                formatter.getFormattedRecords("test"),
                "<table>" +
                        "<tr><th colspan='5'>test</th></tr>" +
                        "<tr><th>Name</th><th>Length</th><th>Amount</th><th>Unit</th><th>Description</th></tr>" +
                        "<tr>" +
                            "<td>"+calculator.getFrontAndBackUpperPillars().getName()+"</td>" +
                            "<td>"+calculator.getFrontAndBackUpperPillars().getLength()+"</td>" +
                            "<td>"+calculator.getFrontAndBackUpperPillars().getCount()+"</td>" +
                            "<td>"+PartsTableFormatter.unit+"</td>" +
                            "<td>"+calculator.getFrontAndBackUpperPillars().getDescription()+"</td>" +
                        "</tr>" +
                        "</table>"
        );
        System.out.println(formatter.getFormattedRecords("test"));
    }

}