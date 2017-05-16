package org.cba.model.carport.formating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public class PartsTableFormatter implements PartRecordsFormatter {
    private List<PartRecord> partRecords = new ArrayList<>();
    private StringBuilder tableBuilder;
    public static final String unit = "Stk.";

    @Override
    public void addPartRecord(PartRecord partRecord) {
        partRecords.add(partRecord);
    }

    @Override
    public String getFormattedRecords(String tableName) {
        tableBuilder = new StringBuilder();
        tableBuilder.append("<table>");
        addHeader(tableName, "Name,Length,Amount,Unit,Description");
        addRows();
        tableBuilder.append("</table>");
        return tableBuilder.toString();
    }

    private void addHeader(String tableName, String columnTitles) {
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

    private void addRows() {
        for (PartRecord partRecord : partRecords) {
            addRow(partRecord);
        }
    }

    private void addRow(PartRecord partRecord) {
        tableBuilder.append("<tr>");
        addColumn(partRecord.getName());
        addColumn(String.valueOf(partRecord.getLength()));
        addColumn(String.valueOf(partRecord.getCount()));
        addColumn(unit);
        addColumn(partRecord.getDescription());
        tableBuilder.append("</tr>");
    }

    private void addColumn(String record) {
        tableBuilder.append("<td>").append(record).append("</td>");
    }

    @Override
    // TODO: probably have sepparate class for dependencies, because roof tiles might also have dependencies
    public String printAssemblyMaterialTable(String tableName) {
        return null;
    }
}
