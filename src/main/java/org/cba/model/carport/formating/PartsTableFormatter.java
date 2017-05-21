package org.cba.model.carport.formating;

import org.cba.model.carport.formating.table.Row;
import org.cba.model.carport.formating.table.TableBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public class PartsTableFormatter implements PartsFormatter {
    private static final String unit = "Stk.";
    private List<PartRecord> partRecords = new ArrayList<>();
    private TableBuilder tableBuilder;

    @Override
    public void addPartRecord(PartRecord partRecord) {
        partRecords.add(partRecord);
    }

    @Override
    public String getFormattedRecords(String tableName) {
        tableBuilder = new TableBuilder();
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
        row.addColumn(String.valueOf(partRecord.getLength()));
        row.addColumn(String.valueOf(partRecord.getCount()));
        row.addColumn(unit);
        row.addColumn(partRecord.getDescription());
    }
}
