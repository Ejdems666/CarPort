package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.StaticMaterial;
import org.cba.domain.query.QStaticMaterial;

public class StaticMaterialFinder extends Finder<Integer,StaticMaterial> {

  /**
   * Construct using the default EbeanServer.
   */
  public StaticMaterialFinder() {
    super(StaticMaterial.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public StaticMaterialFinder(String serverName) {
    super(StaticMaterial.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QStaticMaterial where() {
     return new QStaticMaterial(db());
  }

  /**
   * Start a new document store query.
   */
  public QStaticMaterial text() {
     return new QStaticMaterial(db()).text();
  }
}
