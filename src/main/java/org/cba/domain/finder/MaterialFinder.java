package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.Material;
import org.cba.domain.query.QMaterial;

public class MaterialFinder extends Finder<Integer, Material> {

    /**
     * Construct using the default EbeanServer.
     */
    public MaterialFinder() {
        super(Material.class);
    }

    /**
     * Construct with a given EbeanServer.
     */
    public MaterialFinder(String serverName) {
        super(Material.class, serverName);
    }

    /**
     * Start a new typed query.
     */
    public QMaterial where() {
        return new QMaterial(db());
    }

    /**
     * Start a new document store query.
     */
    public QMaterial text() {
        return new QMaterial(db()).text();
    }
}
