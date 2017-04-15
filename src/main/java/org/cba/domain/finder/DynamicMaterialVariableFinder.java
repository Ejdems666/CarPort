package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.DynamicMaterialVariable;
import org.cba.domain.query.QDynamicMaterialVariable;

public class DynamicMaterialVariableFinder extends Finder<Integer, DynamicMaterialVariable> {

    /**
     * Construct using the default EbeanServer.
     */
    public DynamicMaterialVariableFinder() {
        super(DynamicMaterialVariable.class);
    }

    /**
     * Construct with a given EbeanServer.
     */
    public DynamicMaterialVariableFinder(String serverName) {
        super(DynamicMaterialVariable.class, serverName);
    }

    /**
     * Start a new typed query.
     */
    public QDynamicMaterialVariable where() {
        return new QDynamicMaterialVariable(db());
    }

    /**
     * Start a new document store query.
     */
    public QDynamicMaterialVariable text() {
        return new QDynamicMaterialVariable(db()).text();
    }
}
