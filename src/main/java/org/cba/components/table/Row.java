package org.cba.components.table;

/**
 * Created by adam on 20/05/2017.
 */
public class Row {
    private StringBuilder rowBuilder = new StringBuilder("<tr>");

    public void addColumn(String record) {
        rowBuilder.append("<td>").append(record).append("</td>");
    }

    @Override
    public String toString() {
        return rowBuilder.toString() + "</tr>";
    }
}
