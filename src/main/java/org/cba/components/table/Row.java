package org.cba.components.table;

import org.cba.Path;

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
        addColumnLink(url, icon, Path.ROOT);
    }

    public void addColumnLink(String url, Icon icon, Path path) {
        url = path + url;
        rowBuilder.append("<td><a href='").append(url).append("'>")
                .append("<i class=\"fa fa-").append(icon).append("\" aria-hidden=\"true\"></i></a>")
                .append("</td>");
    }

    @Override
    public String toString() {
        return rowBuilder.toString() + "</tr>";
    }

    public enum Icon {
        DELETE("times"), EDIT("pencil"), PDF("file-pdf-o");
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
