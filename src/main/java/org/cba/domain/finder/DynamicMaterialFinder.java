package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.DynamicMaterial;
import org.cba.domain.query.QDynamicMaterial;

public class DynamicMaterialFinder extends Finder<Integer,DynamicMaterial> {

  /**
   * Construct using the default EbeanServer.
   */
  public DynamicMaterialFinder() {
    super(DynamicMaterial.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public DynamicMaterialFinder(String serverName) {
    super(DynamicMaterial.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QDynamicMaterial where() {
     return new QDynamicMaterial(db());
  }

  /**
   * Start a new document store query.
   */
  public QDynamicMaterial text() {
     return new QDynamicMaterial(db()).text();
  }
}
