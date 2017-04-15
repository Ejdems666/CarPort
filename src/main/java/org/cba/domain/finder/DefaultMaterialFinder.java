package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.DefaultMaterial;
import org.cba.domain.query.QDefaultMaterial;

public class DefaultMaterialFinder extends Finder<Integer, DefaultMaterial> {

    /**
     * Construct using the default EbeanServer.
     */
    public DefaultMaterialFinder() {
        super(DefaultMaterial.class);
    }

    /**
     * Construct with a given EbeanServer.
     */
    public DefaultMaterialFinder(String serverName) {
        super(DefaultMaterial.class, serverName);
    }

    /**
     * Start a new typed query.
     */
    public QDefaultMaterial where() {
        return new QDefaultMaterial(db());
    }

    /**
     * Start a new document store query.
     */
    public QDefaultMaterial text() {
        return new QDefaultMaterial(db()).text();
    }
}
