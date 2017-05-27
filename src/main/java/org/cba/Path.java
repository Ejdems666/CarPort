package org.cba;

/**
 * Created by adam on 26/05/2017.
 */
public enum Path {
    // If PDF path is changed, path in pdf generator must be changed as well!
    ROOT("/"), ASSETS(ROOT + "assets/"), CP_IMG(ASSETS + "carport-images/"), PDF(ASSETS + "pdf/"),
    GENERATING_PDF("assets/pdf/"), URL("http://localhost:8080");
    private String value;

    Path(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
