package org.cba.components.table;

/**
 * Created by adam on 20/05/2017.
 */
public class Row {
    private StringBuilder rowBuilder = new StringBuilder("<tr>");

    public void addColumn(Object record) {
        addColumn(String.valueOf(record));
    }

    public void addColumn(String record) {
        rowBuilder.append("<td>").append(record).append("</td>");
    }

    public void addColumnLink(String url, Icon icon) {
        rowBuilder.append("<td><a href='").append(url).append("'>")
                .append("<i class=\"fa fa-").append(icon).append("\" aria-hidden=\"true\"></i></a>")
                .append("</td>");
    }

    @Override
    public String toString() {
        return rowBuilder.toString() + "</tr>";
    }

    public enum Icon{
        DELETE("times"), EDIT("pencil");
        private String icon;

        Icon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return icon;
        }
    }
}
