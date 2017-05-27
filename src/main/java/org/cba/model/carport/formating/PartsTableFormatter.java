package org.cba.model.carport.formating;

import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public class PartsTableFormatter implements PartsFormatter {
    private static final String unit = "Stk.";
    private List<PartRecord> partRecords = new ArrayList<>();
    private TableBuilder tableBuilder;
    private String tableName;

    public PartsTableFormatter(String tableName) {
        this.tableName = tableName;
        tableBuilder = new TableBuilder();
    }
    public PartsTableFormatter(String tableName, TableBuilder tableBuilder) {
        this.tableName = tableName;
        this.tableBuilder = tableBuilder;
    }

    @Override
    public void addPartRecord(PartRecord partRecord) {
        partRecords.add(partRecord);
    }

    @Override
    public String getFormattedRecords() {
        tableBuilder.addHeader(tableName, "Name,Length,Amount,Unit,Description");
        addRows();
        return tableBuilder.toString();
    }

    private void addRows() {
        for (PartRecord partRecord : partRecords) {
            addRow(partRecord);
        }
    }

    private void addRow(PartRecord partRecord) {
        Row row = tableBuilder.createNewRow();
        row.addColumn(partRecord.getName());
        if (partRecord instanceof RoofTileRecord) {
            row.addColumn("NaN");
        } else {
            row.addColumn(partRecord.getLength());
        }
        row.addColumn(partRecord.getCount());
        row.addColumn(unit);
        row.addColumn(partRecord.getDescription());
    }
}
