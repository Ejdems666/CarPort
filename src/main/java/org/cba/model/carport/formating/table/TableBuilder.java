package org.cba.model.carport.formating.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 20/05/2017.
 */
public class TableBuilder {
    private StringBuilder tableBuilder = new StringBuilder("<table>");
    private List<Row> rows = new ArrayList<>();

    public Row createNewRow() {
        Row row = new Row();
        rows.add(row);
        return row;
    }

    public void addHeader(String tableName, String columnTitles) {
        String[] columnTitlesSplit = columnTitles.split(",");
        addTableName(tableName, columnTitlesSplit.length);
        tableBuilder.append("<tr>");
        for (String heading : columnTitlesSplit) {
            tableBuilder.append("<th>").append(heading).append("</th>");
        }
        tableBuilder.append("</tr>");
    }

    private void addTableName(String tableName, int numberOfColumns) {
        tableBuilder.append("<tr><th colspan='").append(numberOfColumns).append("'>")
                .append(tableName)
                .append("</th></tr>");
    }

    @Override
    public String toString() {
        StringBuilder finalHtml = new StringBuilder(tableBuilder);
        for (Row row : rows) {
            finalHtml.append(row);
        }
        finalHtml.append("</table>");
        return finalHtml.toString();
    }
}
