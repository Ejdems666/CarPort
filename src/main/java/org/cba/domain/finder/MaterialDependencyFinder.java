package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.MaterialDependency;
import org.cba.domain.query.QMaterialDependency;

public class MaterialDependencyFinder extends Finder<Integer, MaterialDependency> {

    /**
     * Construct using the default EbeanServer.
     */
    public MaterialDependencyFinder() {
        super(MaterialDependency.class);
    }

    /**
     * Construct with a given EbeanServer.
     */
    public MaterialDependencyFinder(String serverName) {
        super(MaterialDependency.class, serverName);
    }

    /**
     * Start a new typed query.
     */
    public QMaterialDependency where() {
        return new QMaterialDependency(db());
    }

    /**
     * Start a new document store query.
     */
    public QMaterialDependency text() {
        return new QMaterialDependency(db()).text();
    }
}
