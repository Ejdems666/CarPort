package org.cba.components.table;

import hyggemvc.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 20/05/2017.
 */
public class TableBuilder implements Component {
    private StringBuilder tableBuilder;
    private List<Row> rows = new ArrayList<>();

    public TableBuilder(String classes) {
        tableBuilder = new StringBuilder();
        tableBuilder.append("<table class='").append(classes).append("'>");
    }

    public TableBuilder() {
        tableBuilder = new StringBuilder("<table>");
    }

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
