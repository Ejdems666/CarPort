package org.cba.components;

import hyggemvc.component.Component;

/**
 * Created by adam on 21/05/2017.
 */
public class SelectBuilder implements Component {
    private StringBuilder selectBuilder;
    private Object defaultValue;

    public SelectBuilder(String label, String name, Object defaultValue) {
        createLabelAndOpeningSelectTag(label, name);
        selectBuilder.append(">");
        this.defaultValue = defaultValue;
    }

    private void createLabelAndOpeningSelectTag(String label, String name) {
        selectBuilder = new StringBuilder("<label for='").append(name).append("'>").append(label).append("</label>")
                .append("<select id='").append(name).append("' class='selectpicker' name='").append(name).append("' ");
    }

    public SelectBuilder(String label, String name, Object defaultValue, String attributes) {
        createLabelAndOpeningSelectTag(label,name);
        selectBuilder.append(attributes).append(">");
        this.defaultValue = defaultValue;
    }

    public void addOption(Object value, Object label) {
        if (defaultValue.equals(value)) {
            selectBuilder.append("<option selected ");
        } else {
            selectBuilder.append("<option ");
        }
        selectBuilder.append("value='").append(value).append("'>").append(label).append("</option>");
    }

    @Override
    public String toString() {
        return selectBuilder.toString() + "</select>";
    }
}
