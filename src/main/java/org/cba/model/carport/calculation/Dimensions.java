package org.cba.model.carport.calculation;

/**
 * Created by adam on 22/05/2017.
 */
public class Dimensions {
    public int length;
    public int width;

    public Dimensions(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public String toHTTPGetForm(String prefix) {
        return prefix + "Length=" + length + "&" + prefix + "Width=" + width;
    }
}
