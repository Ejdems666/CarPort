package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.MaterialLength;
import org.cba.domain.query.QMaterialLength;

public class MaterialLengthFinder extends Finder<Integer,MaterialLength> {

  /**
   * Construct using the default EbeanServer.
   */
  public MaterialLengthFinder() {
    super(MaterialLength.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public MaterialLengthFinder(String serverName) {
    super(MaterialLength.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QMaterialLength where() {
     return new QMaterialLength(db());
  }

  /**
   * Start a new document store query.
   */
  public QMaterialLength text() {
     return new QMaterialLength(db()).text();
  }
}
